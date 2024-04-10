package shop.mtcoding.blog.controller.user;

import lombok.Data;

@Data
public class SessionUser {
    private Integer id;
    private String username;
    private String email;

    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
