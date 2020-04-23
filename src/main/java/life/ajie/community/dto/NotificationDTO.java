package life.ajie.community.dto;

import life.ajie.community.model.User;
import lombok.Data;

@Data
public class NotificationDTO {

    private Long id;
    private Long gmtCreate;
    private Integer status;
    private long notifier;
    private String notifierName;
    private String outerTitle;
    private long outerId;
    private String typeName;
    private Integer type;
}
