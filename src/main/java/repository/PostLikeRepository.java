package repository;

import entity.PostLikeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends PagingAndSortingRepository<PostLikeEntity, Long> {
    Optional<PostLikeEntity> findPostLikeEntitiesByPostId(Long postId);

    List<PostLikeEntity> findAllByPostIdAndLiked(Long postId, boolean liked);
    PostLikeEntity findAllByPostIdAndUserId(Long postId, String userId);

    List<PostLikeEntity> findAllByPostIdAndUserIdAndLiked(Long postId, String userId, boolean liked);
}
