package shop.mtcoding.blog.controller.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    @Transactional
    public void update(Integer boardId, BoardRequest.UpdateDTO requestDTO) {
        Board board = boardJPARepository.findById(boardId).orElseThrow(() -> new Exception404("존재하지 않는 글입니다."));

        board.setTitle(requestDTO.getTitle());
        board.setContent(requestDTO.getContent());
    }

    @Transactional(readOnly = true)
    public BoardResponse.UpdateFormDTO updateForm(Integer boardId, Integer sessionUserId) {
        Board board = boardJPARepository.findById(boardId).orElseThrow(() -> new Exception404("존재하지 않는 글입니다."));
        if (board.getUser().getId() != sessionUserId) {
            throw new Exception403("글을 수정할 권한이 없습니다.");
        }

        BoardResponse.UpdateFormDTO updateFormDTO = new BoardResponse.UpdateFormDTO(board);

        return updateFormDTO;
    }

    public List<BoardResponse.MainDTO> findAllBoard() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        List<BoardResponse.MainDTO> responseDTO = new ArrayList<>();

        boardJPARepository.findAll(sort).stream().forEach(board -> responseDTO.add(new BoardResponse.MainDTO(board)));

        return responseDTO;
    }

    ;
}
