package com.user.consumer.controller;

import com.wzl.commons.model.TokenUser;
import com.wzl.commons.utlity.TokenUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


public class BaseController {
    protected TokenUser CurrUser;
    @ModelAttribute
    public void  setResAndReq(HttpServletRequest request, HttpServletResponse response){
        String BEARER_IDENTIFIER = "Bearer "; // space is important

        Enumeration<String> authorizationList =request.getHeaders("Authorization");
        String authorization=authorizationList.nextElement();
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith(BEARER_IDENTIFIER)) {
//            System.out.println("Token 验证通过 ..."+rui+request.getPath());
//
            TokenUtil tokenUtil=new TokenUtil();
            String jwt = authorization.substring(BEARER_IDENTIFIER.length());
            this.CurrUser=tokenUtil.parseUserFromToken(jwt);

        }
//        Object obj=request.getAttribute("Claims_user");
//        if(obj !=null){
//            this.CurrUser=(TokenUser)obj;
//
//
//        }
    }
}
