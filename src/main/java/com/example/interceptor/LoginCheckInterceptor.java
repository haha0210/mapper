package com.example.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Result;
import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override  //目标资源运行前运行,返回true：放行，返回false:不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1--- 获取请求的url
       String url = request.getRequestURL().toString();
       log.info("请求的url:{}",url);

        //2-------判断是否为登录
       if(url.contains("login")){
           log.info("登录操作,放行.....");
           return true;
       }

       //3---- 获取请求头中的令牌
       String jwt = request.getHeader("token");

       //4------ 判断令牌是否存在
       if(!StringUtils.hasLength(jwt)){
           log.info("请求头token为空，返回未登录的信息");
           Result error = Result.error("NOT_LOGIN");
           String notLogin = JSONObject.toJSONString(error);
           response.getWriter().write(notLogin);
           return false;
       }

       //5------ 解析token，解析失败则返回错误结果
       try{
           JwtUtils.parseJWT(jwt);
       }catch (Exception e){
           e.printStackTrace();
           log.info("解析令牌失败，返回未登录错误信息");
           Result error = Result.error("NOT_LOGIN");
           String notLogin = JSONObject.toJSONString(error);
           response.getWriter().write(notLogin);
           return false;
        }

       //6----  放行
       log.info("令牌合法，放行");
       return true;
    }

    @Override  //目标资源运行后放行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("posthandle....");
    }

    @Override  //摄图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("aftercompletion....");
    }
}
