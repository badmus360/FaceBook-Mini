package service.impl;

import entity.CommentLikeEntity;
import exception.ErrorMessages;
import lombok.AllArgsConstructor;
import model.response.ApiResponse;
import model.response.CommentLikeRest;
import model.response.ResponseManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import repository.CommentLikeRepository;
import repository.CommentRepository;
import repository.PostRepository;
import repository.UserRepository;
import service.CommentLikeService;

@Service
@AllArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final ResponseManager<CommentLikeRest> responseManager;

    private final UserRepository userRepository;

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<CommentLikeRest> updateCommentLike(Long commentId, Long postId, String userId) {

        if (userRepository.findUserEntitiesByUserId(userId).orElse(null) == null)
            return responseManager.error(HttpStatus.BAD_REQUEST, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        if (postRepository.findById(postId).orElse(null) == null)
            return responseManager.error(HttpStatus.BAD_REQUEST, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        var comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null)
            return responseManager.error(HttpStatus.BAD_REQUEST, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        CommentLikeEntity commentLikeEntity = commentLikeRepository
                .findCommentLikeEntitiesByCommentIdAndPostIdAndUserId(commentId, postId, userId);
        CommentLikeEntity comLike = new CommentLikeEntity();
        comLike.setLiked(true);

        if (commentLikeEntity != null) {
            var like = commentLikeEntity.isLiked();
            comLike.setLiked(!like);
            comLike.setId(commentLikeEntity.getId());
        }

        comLike.setUserId(userId);
        comLike.setPostId(postId);
        comLike.setCommentId(commentId);
        comLike.setComments(comment);

        var createdCommLike = commentLikeRepository.save(comLike);
        CommentLikeDto commentLikeDto = modelMapper.map(createdCommLike, CommentLikeDto.class);
        CommentLikeRest commentLikeRest = modelMapper.map(commentLikeDto, CommentLikeRest.class);

        return responseManager.success(HttpStatus.OK, commentLikeRest);
    }


}
