package com.ysh.garbageRecyle.interceptor;
import com.ysh.garbageRecyle.entity.UsersEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //获取当前的session
        HttpSession session=request.getSession();
        //从session中寻求cur_user变量
        Object obj=session.getAttribute("cur_user");
        //判断是否存在用户变量
        //不存在则跳转到登录界面
        if(obj==null||!(obj instanceof UsersEntity)){
            response.sendRedirect("/users/toLogin");
            return false;
        }else{
            //存在则再次注入session
            session.setAttribute("cur_user",(UsersEntity)obj);
            System.out.println(((UsersEntity) obj).getAccountNumber()+((UsersEntity) obj).getPassword());
        }
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
