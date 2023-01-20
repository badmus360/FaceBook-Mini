package controllers.rest;

import lombok.AllArgsConstructor;
import model.response.ApiResponse;
import model.response.PostLikeRest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.impl.PostLikeServiceImpl;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class PostLikeController {

    private final PostLikeServiceImpl postLikeService;

    @PutMapping(path = "/{userId}/post/{postId}/post-like",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ApiResponse<PostLikeRest> updatePostLike(@PathVariable String userId, @PathVariable Long postId) {
        return postLikeService.updatePostLike(postId, userId);
    }

}
