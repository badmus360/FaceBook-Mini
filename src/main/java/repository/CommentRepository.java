package repository;

import entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Long> {
    Optional<CommentEntity> findCommentEntitiesByPostId(String userId);
    Page<CommentEntity> findAllByPostId(Long postId, Pageable pageable);
    List<CommentEntity> findAllByPostId(Long postId);
}