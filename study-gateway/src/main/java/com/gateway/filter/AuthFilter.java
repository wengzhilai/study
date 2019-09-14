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
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClientResponse;

import java.net.URI;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 鉴权过滤器
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final String BEARER_IDENTIFIER = "Bearer "; // space is important

    TokenUtil tokenUtil=new TokenUtil();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        URI rui= route.getUri();
        String routeId=route.getId();
        ServerHttpRequest request=exchange.getRequest();
        HttpHeaders headers=request.getHeaders();
        String authorization =headers.getFirst("Authorization");

        if (!StringUtils.isEmpty(authorization) && authorization.startsWith(BEARER_IDENTIFIER)) {
            System.out.println("Token 验证通过 ..."+rui+request.getPath());

            String jwt = authorization.substring(BEARER_IDENTIFIER.length());
            TokenUser t=tokenUtil.parseUserFromToken(jwt);

//            ServerHttpRequest newRequest = request.mutate().header("Claims_user",t.getName()).build();
//            HttpServletRequest httpRequest = (HttpServletRequest) request;
//            httpRequest.setAttribute("Claims_user",t);
        }
        else  if(routeId!=null && !checkIgnoreToken(routeId,request.getPath().toString())){

            System.out.println("Token 验证通过不通过 ..."+rui+request.getPath());

            ServerHttpResponse response = exchange.getResponse();
            // 封装错误信息
            Map<String, Object> responseData =  new HashMap<String,Object>();
            responseData.put("code", 401);
            responseData.put("message", "非法请求:"+rui+request.getPath());
            responseData.put("cause", "Token is empty");

            try {
                // 将信息转换为 JSON
                ObjectMapper objectMapper = new ObjectMapper();
                byte[] data = objectMapper.writeValueAsBytes(responseData);

                // 输出错误信息到页面
                DataBuffer buffer = response.bufferFactory().wrap(data);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("忽略Token验证  ..."+rui+request.getPath());
        }
        return chain.filter(exchange);
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