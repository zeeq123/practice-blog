package shop.mtcoding.blog.controller.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.controller.user.SessionUser;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO requestDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        replyService.save(requestDTO, sessionUser);

        return "redirect:/board/" + requestDTO.getBoardId();
    }

    @PostMapping("board/{boardId}/reply/{replyId}/delete")
    public String delete(
            @PathVariable Integer boardId,
            @PathVariable Integer replyId
    ) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        replyService.delete(replyId, sessionUser.getId());

        return "redirect:/board/" + boardId;
    }
}
