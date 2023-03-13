package shop.mtcoding.springetc2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.mtcoding.springetc2.handler.aop.Hello;

@RestController
public class HelloController {
    
    @GetMapping("/v1")
    public String v1(){
        return "v1";
    }

    @Hello
    @GetMapping("/v2")
    public String v2(){
        return "v2";
    }
}
