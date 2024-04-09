package shop.mtcoding.blog.controller.user;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService ;
    private final HttpSession session ;

    @PostMapping("/join") // 회원가입
    public String join(UserRequest.JoinDTO requestDTO){
        User user = userService.createUser(requestDTO);
        session.setAttribute("sessionUser", user);

        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm() {
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
