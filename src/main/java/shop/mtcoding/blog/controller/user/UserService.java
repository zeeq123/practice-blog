package shop.mtcoding.blog.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository ;

    @Transactional // 회원가입
    public User createUser(UserRequest.JoinDTO requestDTO){
        User user = new User(requestDTO);

        return userJPARepository.save(user);
    }
}
