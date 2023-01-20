package service;

import model.response.ApiResponse;
import model.response.CommentLikeRest;

public interface CommentLikeService {
    ApiResponse<CommentLikeRest> updateCommentLike(Long commentId, Long postId, String userId);
}
