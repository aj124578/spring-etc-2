package shop.mtcoding.springetc2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.springetc2.handler.aop.LoginUser;
import shop.mtcoding.springetc2.handler.aop.SessionUser;
import shop.mtcoding.springetc2.model.User;

@RequiredArgsConstructor // final이 붙은 필드는 전부 다 생성자를 만들어주는 어노테이션
@RestController
public class UserController {

    private final HttpSession session; // final이 붙은 전역변수는 new가 될때 값이 있어야 함

    @GetMapping("/login")
    public String login(){
        User user = new User(1, "ssar", "1234", "0102222");
        session.setAttribute("principal", user);
        return "login ok";
    }

    @GetMapping("/user/1")
    public String userInfo(){ // 인증 필요 없음
        return "user ok";
    }

    @GetMapping("/auth/1")
    public String authInfo(@LoginUser User user){ // 인증 필요함
            System.out.println("자동으로 값 주입됨");
            System.out.println(user.getUsername());
        return "auth ok";
    }

    @GetMapping("/auth/ss/1")
    public String authInfo22(@SessionUser User user){ // 인증 필요함
            System.out.println(user.getUsername());
        return "auth ok";
    }

}
