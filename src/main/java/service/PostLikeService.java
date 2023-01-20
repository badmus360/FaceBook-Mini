package service;

public interface PostLikeService {
    ApiResponse<PostLikeRest> updatePostLike(Long postId, String userId);
}
