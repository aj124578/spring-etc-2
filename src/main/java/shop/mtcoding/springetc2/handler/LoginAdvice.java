package shop.mtcoding.springetc2.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import shop.mtcoding.springetc2.model.User;

@Aspect
@Component
public class LoginAdvice {

    @Around("execution(* shop.mtcoding.springetc2.controller..*.*(..))")
    public Object loginUserAdvice(ProceedingJoinPoint jp) throws Throwable {
        Object result = jp.proceed();
        Object[] param = new Object[1];
        Object[] args = jp.getArgs();

        System.out.println("테스트 : " + args.length);
        for (Object arg : args) {
            if (arg instanceof User) {
                HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
                HttpSession session = req.getSession();
                User principal = (User) session.getAttribute("principal");
                param[0] = principal;
                result = jp.proceed(param);
            }
        }

        return result;
    }
}