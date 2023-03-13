package shop.mtcoding.springetc2.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component // 특정한 목적이 없으면 ioc에 띄우기위해 @Component를 붙임
public class HelloAdvice {
    /*
     * shop.mtcoding.springetc2.handler.aop.Hello
     * ↓ 깃발 이름이 너무 길기 때문에 별칭을 줌
     */

    // 깃발에 별칭주기
    @Pointcut("@annotation(shop.mtcoding.springetc2.handler.aop.Hello)")
    public void hello(){}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping(){}


    @Around("hello()")
    public Object helloAdvice(ProceedingJoinPoint jp) throws Throwable{ // ProceedingJoinPoin 리플렉션을 안해도 얘만 붙이면 메서드 정보를 다 찾아줌
        Object[] args = jp.getArgs(); // jp의 args를 꺼내기 위해서 오브젝트 배열 타입으로 받음

        System.out.println("파라메터 사이즈 : " + args.length);

        for (Object arg : args) {
            if (arg instanceof String) {
                String username = (String) arg;
                System.out.println(username + "님 안녕");
            }
        }

        return jp.proceed();
    }

}
