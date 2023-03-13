package shop.mtcoding.springetc2.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import shop.mtcoding.springetc2.model.User;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().print("잘못된 접근입니다.");
            return false;
        } else {
            return true; // 다시 메서드에 진입
        }
    }
}
