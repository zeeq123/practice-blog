package shop.mtcoding.blog.controller.reply;

import lombok.Data;
import shop.mtcoding.blog.controller.board.Board;
import shop.mtcoding.blog.controller.user.SessionUser;
import shop.mtcoding.blog.controller.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String comment;

        public Reply toEntity(SessionUser sessionUser, Board board) {
            return Reply.builder()
                    .board(board)
                    .user(
                            User.builder()
                                    .id(sessionUser.getId())
                                    .username(sessionUser.getUsername())
                                    .email(sessionUser.getEmail())
                                    .build()
                    )
                    .comment(comment)
                    .build();

        }
    }
}
