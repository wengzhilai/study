package com.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gateway.model.TokenUser;
import com.gateway.utlity.TokenUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 鉴权过滤器
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final String BEARER_IDENTIFIER = "Bearer "; // space is important
    private static final String ALL = "*";
    private static final String MAX_AGE = "18000L";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request=exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        HttpHeaders responseHeaders = response.getHeaders();
        responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        responseHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ALL);
        responseHeaders.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);

        if (request.getMethod() == HttpMethod.OPTIONS) {
            response.setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }


        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        URI rui= route.getUri();
        String routeId=route.getId();
        String authorization =request.getHeaders().getFirst("Authorization");

        if (!StringUtils.isEmpty(authorization) && authorization.startsWith(BEARER_IDENTIFIER)) {

            String jwt = authorization.substring(BEARER_IDENTIFIER.length());
            TokenUser t = new TokenUtil().parseUserFromToken(jwt);
            if (t == null) {
                System.out.println("Token 已过期 ..." + rui + request.getPath());
                byte[] data = getErrByte(401, "Token 已过期", "时间过期");
                DataBuffer buffer = response.bufferFactory().wrap(data);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            }
            System.out.println("Token 验证通过 ..." + rui + request.getPath());
        }
        else  if(routeId!=null && !checkIgnoreToken(routeId,request.getPath().toString())){

            System.out.println("Token 验证通过不通过 ..."+rui+request.getPath());
            byte[] data=getErrByte(401,"非法请求:"+rui+request.getPath(),"Token is empty");
            DataBuffer buffer = response.bufferFactory().wrap(data);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        else {
            System.out.println("忽略Token验证  ..."+rui+request.getPath());
        }

        return chain.filter(exchange);
    }

    /**
     * 封装错误信息
     * @param code 代码
     * @param message 错误信息
     * @param cause 原因
     * @return
     */
    public byte[] getErrByte(int code,String message,String cause){
        Map<String, Object> responseData =  new HashMap<String,Object>();
        responseData.put("code", code);
        responseData.put("message", message);
        responseData.put("cause", cause);

        try {
            // 将信息转换为 JSON
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsBytes(responseData);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 设置过滤器的执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private  Boolean checkIgnoreToken(String routeId,String path){
        Set<String> ignoreToken=new TreeSet<>();
        ignoreToken.add(".*-consumer:/swagger-ui.html.*");
        ignoreToken.add(".*-consumer:/swagger-resources.*");
        ignoreToken.add(".*-consumer:/v2/api-docs.*");
//
//        ignoreToken.add("user-consumer:/swagger-ui.html.*");
//        ignoreToken.add("user-consumer:/swagger-resources.*");
        ignoreToken.add("user-consumer:/Login/userLogin.*");


//        return true;

        if( Pattern.matches("/null/.*", path) || Pattern.matches("/webjars/.*", path)){
            return true;
        }

        boolean isTrue=false;
        for (String s : ignoreToken) {
            isTrue=Pattern.matches(s, routeId+":"+path);
            if(isTrue)return true;
        }
        return false;

    }
}