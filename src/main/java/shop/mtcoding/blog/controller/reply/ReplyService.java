package shop.mtcoding.blog.controller.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.controller.board.Board;
import shop.mtcoding.blog.controller.board.BoardJPARepository;
import shop.mtcoding.blog.controller.user.SessionUser;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;
    private final BoardJPARepository boardJPARepository;

    @Transactional
    public void save(ReplyRequest.SaveDTO requestDTO, SessionUser sessionUser) {
        Board board = boardJPARepository.findById(requestDTO.getBoardId()).orElseThrow(() -> new Exception404("글이 존재하지 않습니다."));

        replyJPARepository.save(requestDTO.toEntity(sessionUser, board));
    }

    @Transactional
    public void delete(Integer replyId, Integer sessionUserId) {
        Reply reply = replyJPARepository.findById(replyId).orElseThrow(() -> new Exception404("존재하지 않는 댓글입니다."));

        if (reply.getUser().getId() != sessionUserId) {
            throw new Exception403("댓글을 삭제할 권한이 없습니다.");
        }

        replyJPARepository.deleteById(replyId);
    }
}
