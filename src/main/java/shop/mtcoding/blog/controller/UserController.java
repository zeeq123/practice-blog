package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/joinForm")
    public String joinForm() {
        return "join-form";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "login-form";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
