package shop.mtcoding.springetc2.handler;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

    @Before("hello()") // argNames이 pointcut을 말함
    public void helloAdvice(){
        System.out.println("안녕안녕");
    }

    @After("getMapping() || hello()") 
    public void getAdvice(){
        System.out.println("헉헉");
    }


}
