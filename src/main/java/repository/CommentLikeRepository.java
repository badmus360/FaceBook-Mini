package repository;

import entity.CommentLikeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentLikeRepository extends PagingAndSortingRepository<CommentLikeEntity, Long> {
    Optional<CommentLikeEntity> findCommentLikeEntitiesByCommentId(Long commentId);
    List<CommentLikeEntity> findAllByPostIdAndLiked(Long postId, boolean liked);
    List<CommentLikeEntity> findAllByCommentIdAndLiked(Long commentId, boolean liked);

    List<CommentLikeEntity> findAllByPostId(Long postId);

    CommentLikeEntity findCommentLikeEntitiesByCommentIdAndPostIdAndUserId(Long commentId, Long postId, String userId);
}
