package model.response;

import lombok.Data;

@Data
public class CommentLikeRest {
    private Long id;
    private boolean liked;
    private int postId;
    private String userId;
}
