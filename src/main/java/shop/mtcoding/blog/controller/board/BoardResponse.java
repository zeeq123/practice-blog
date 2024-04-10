package shop.mtcoding.blog.controller.board;

import lombok.Data;

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
}
