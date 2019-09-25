package com.user.consumer.controller;

import com.wzl.commons.model.TokenUser;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseController {
    protected TokenUser CurrUser;
    @ModelAttribute
    public void  setResAndReq(HttpServletRequest request, HttpServletResponse response){

        Object obj=request.getAttribute("Claims_user");
        if(obj !=null){
            this.CurrUser=(TokenUser)obj;


        }
    }
}
