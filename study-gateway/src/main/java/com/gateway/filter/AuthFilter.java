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
import java.util.HashMap;
import java.util.Map;

/**
 * 鉴权过滤器
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final String BEARER_IDENTIFIER = "Bearer "; // space is important

    TokenUtil tokenUtil=new TokenUtil();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String tmp=exchange.getLogPrefix();
        Map<String, Object> tmp1=exchange.getAttributes();
        Mono<WebSession> tmp2=exchange.getSession();

        Route clientResponse = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String id=clientResponse.getId();
        URI rui= clientResponse.getUri();
        ServerHttpRequest request=exchange.getRequest();
        HttpHeaders headers=request.getHeaders();
        String authorization =headers.getFirst("Authorization");
        System.out.println("Inside JWT interceptor, checking request ..."+rui+request.getPath());

        if (!StringUtils.isEmpty(authorization) && authorization.startsWith(BEARER_IDENTIFIER)) {
            String jwt = authorization.substring(BEARER_IDENTIFIER.length());
            TokenUser t=tokenUtil.parseUserFromToken(jwt);
//            HttpServletRequest httpRequest = (HttpServletRequest) request;
//            httpRequest.setAttribute("Claims_user",t);
        }
        else {

//            ServerHttpResponse response = exchange.getResponse();
//            // 封装错误信息
//            Map<String, Object> responseData =  new HashMap<String,Object>();
//            responseData.put("code", 401);
//            responseData.put("message", "非法请求:"+rui+request.getPath());
//            responseData.put("cause", "Token is empty");
//
//            try {
//                // 将信息转换为 JSON
//                ObjectMapper objectMapper = new ObjectMapper();
//                byte[] data = objectMapper.writeValueAsBytes(responseData);
//
//                // 输出错误信息到页面
//                DataBuffer buffer = response.bufferFactory().wrap(data);
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//                return response.writeWith(Mono.just(buffer));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
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
}