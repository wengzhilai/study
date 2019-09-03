package com.wjbjp.aspect;

import com.wjbjp.mapper.MapperHelper;
import com.wjbjp.server.impl.MapperHelperImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;

@Aspect
@Component
public class WebLogAspect {

    

//    WebLogAspectprivate Logger logger = Logger.getLogger(getClass());(
    @Pointcut("execution(public * com.wjbjp.family.*.*Controller.*(..))")
    public void PointCut(){

    }

    @Before("PointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("---------------request----------------");
//        System.out.println("JSON : " + buffer.toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            System.out.println("name:" + name + "value" + request.getParameter(name));
        }
    }
    @AfterReturning(returning = "ret", pointcut = "PointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        System.out.println("---------------response----------------");
        // 处理完请求，返回内容
        System.out.println("RESPONSE : " + ret);

    }
}
