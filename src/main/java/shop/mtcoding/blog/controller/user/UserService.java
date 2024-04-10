package shop.mtcoding.blog.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository;

    @Transactional
    public void updateUser(Integer id, UserRequest.UpdateDTO requestDTO) {
        User user = userJPARepository.findById(id).orElseThrow(() -> new Exception404("존재하지 않는 유저입니다."));

        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());
    }

    public User login(UserRequest.LoginDTO requestDTO) {
        User user = userJPARepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword()).orElseThrow(() -> new Exception401("아이디 또는 비밀번호가 틀렸습니다."));

        return user;
    }

    @Transactional // 회원가입
    public User createUser(UserRequest.JoinDTO requestDTO) {
        Optional<User> userOP = userJPARepository.findByUsername(requestDTO.getUsername());

        if (userOP.isPresent()) {
            throw new Exception400("이미 존재하는 유저입니다.");
        }

        User user = User.builder()
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .email(requestDTO.getEmail())
                .build();

        return userJPARepository.save(user);
    }
}
