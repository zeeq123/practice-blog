package shop.mtcoding.blog.controller.board;

import lombok.Data;
import shop.mtcoding.blog.controller.reply.Reply;

import java.util.List;

public class BoardResponse {

    @Data
    public static class MainDTO {
        private Integer boardId;
        private String title;

        public MainDTO(Board board) {
            this.boardId = board.getId();
            this.title = board.getTitle();
        }
    }

    @Data
    public static class UpdateFormDTO {
        private Integer id;
        private String title;
        private String content;

        public UpdateFormDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;

        private String username;

        private List<Reply> replyList;

    }
}
