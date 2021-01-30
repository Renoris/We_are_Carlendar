package study.bj.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURL().toString();
        HttpSession session = request.getSession();
        if(requestUrl.contains("/login")||requestUrl.contains("/images")) {
            return true;
        }
        else {
            Object obj = session.getAttribute("userinfo");
            if (obj == null) {
                response.sendRedirect("/login");
                return false; // 더이상 컨트롤러 요청으로 가지 않도록 false로 반환함
            }
            else{
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
