package shop.mtcoding.blog.controller.user;

import lombok.Data;

@Data
public class SessionUser {
    private Integer id;
    private String username;

    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
