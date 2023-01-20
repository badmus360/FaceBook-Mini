package model.response;

import lombok.Data;

@Data
public class PostLikeRest {
    private Long id;
    private boolean liked;
    private Long postId;
    private String userId;
}