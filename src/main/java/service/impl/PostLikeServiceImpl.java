package service.impl;

import entity.PostEntity;
import entity.PostLikeEntity;
import entity.UserEntity;
import lombok.AllArgsConstructor;
import model.response.ApiResponse;
import model.response.PostLikeRest;
import model.response.ResponseManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import repository.PostLikeRepository;
import repository.PostRepository;
import repository.UserRepository;
import service.PostLikeService;

@Service
@AllArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ResponseManager<PostLikeRest> responseManager;
    private final ModelMapper modelMapper;


    @Override
    public ApiResponse<PostLikeRest> updatePostLike(Long postId, String userId) {

        UserEntity userEntity = userRepository.findUserEntitiesByUserId(userId).orElse(null);
        if (userEntity == null)
            return responseManager.error(HttpStatus.BAD_REQUEST, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        PostEntity postEntity = postRepository.findById(postId).orElse(null);
        if(postEntity == null)
            return responseManager.error(HttpStatus.BAD_REQUEST, ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        PostLikeEntity postLikeEntity = postLikeRepository.findAllByPostIdAndUserId(postId, userId);
        PostLikeEntity postLike = new PostLikeEntity();
        postLike.setLiked(true);
        if (postLikeEntity != null) {
            var like = postLikeEntity.isLiked();
            postLike.setLiked(!like);
            postLike.setId(postLikeEntity.getId());
        }

        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLike.setPosts(postEntity);

        PostLikeEntity updatedPostLike = postLikeRepository.save(postLike);
        PostLikeDto postLikeDto = modelMapper.map(updatedPostLike, PostLikeDto.class);
        PostLikeRest postLikeRest = modelMapper.map(postLikeDto, PostLikeRest.class);

        return responseManager.success(HttpStatus.CREATED, postLikeRest);
    }

}
