package life.ajie.community.model;

import lombok.Data;

@Data
public class Comment {
   private Long id;
   private Long parentId;
   private Long gmtCreate;
   private Long gmtModified;
   private Long likeCount;
   private int type;
   private Long commentator;
   private Integer commentCount;
   private String content;

}
