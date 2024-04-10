package shop.mtcoding.blog.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository;

    public User login(UserRequest.LoginDTO requestDTO) {
        User user = userJPARepository.findByUsernameAndPassword().get();

        return user;
    }

    @Transactional // 회원가입
    public User createUser(UserRequest.JoinDTO requestDTO) {
        User user = new User(requestDTO);

        return userJPARepository.save(user);
    }
}
