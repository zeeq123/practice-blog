package shop.mtcoding.blog.controller.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.mtcoding.blog.controller.user.SessionUser;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;

    @GetMapping({"/"})
    public String index(HttpServletRequest request) {
        List<BoardResponse.MainDTO> responseDTO = boardService.findAllBoard();

        request.setAttribute("boardList", responseDTO);

        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{boardId}")
    public String detail(@PathVariable Integer boardId) {
        return "board/detail";
    }

    @GetMapping("/board/{boardId}/update-form")
    public String updateForm(@PathVariable Integer boardId, HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        BoardResponse.UpdateFormDTO updateFormDTO = boardService.updateForm(boardId, sessionUser.getId());

        request.setAttribute("updateFormDTO", updateFormDTO);

        return "board/update-form";
    }
}
