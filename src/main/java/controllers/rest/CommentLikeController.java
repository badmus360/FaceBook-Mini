package controllers.rest;

import lombok.AllArgsConstructor;
import model.response.ApiResponse;
import model.response.CommentLikeRest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.impl.CommentLikeServiceImpl;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class CommentLikeController {

    private final CommentLikeServiceImpl commentLikeService;

    @PutMapping(path = "/{userId}/post/{postId}/comment/{commentId}/comment-like",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ApiResponse<CommentLikeRest> updateCommentLike(@PathVariable Long commentId, @PathVariable Long postId,
                                                          @PathVariable String userId) {
        return commentLikeService.updateCommentLike(commentId, postId, userId);
    }


}
