package shop.mtcoding.springetc2.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import shop.mtcoding.springetc2.handler.aop.LoginUser;
import shop.mtcoding.springetc2.model.User;

@Aspect
@Component
public class LoginAdvice {

    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Around("execution(* shop.mtcoding.aopstudy.controller..*.*(..))")
    public Object loginUserAdvice(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs(); // 메서드마다 10개, 100개 몇개인줄 모르니까 Object 타입의 배열로 받음

        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        
        Annotation[][] annotationsPA = method.getParameterAnnotations();

        for (int i = 0; i < args.length; i++) {
            Annotation[] annotations = annotationsPA[i]; // 첫번째 파라메터의 어노테이션, 두번째 파라메터의 어노테이션
            for (Annotation anno : annotations) {
                if (anno instanceof LoginUser) {
                    HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                            .getRequest();
                    HttpSession session = req.getSession();
                    User principal = (User) session.getAttribute("principal");
                    return jp.proceed(new Object[] { principal });
                }
            }
        }

        return jp.proceed();
    }

}