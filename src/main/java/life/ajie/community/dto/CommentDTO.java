package life.ajie.community.dto;

import life.ajie.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private long id;
    private long parentId;
    private Integer type;
    private long commentator;
    private long gmtCreate;
    private long gmtModified;
    private long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}
