package life.ajie.community.service;

import life.ajie.community.Exception.CustomizeErrorCode;
import life.ajie.community.Exception.CustomizeException;
import life.ajie.community.dto.NotificationDTO;
import life.ajie.community.dto.PaginationDTO;
import life.ajie.community.dto.QuestionDTO;
import life.ajie.community.enums.NotificationStatusEnum;
import life.ajie.community.enums.NotificationTypeEnum;
import life.ajie.community.mapper.NotificationMapper;
import life.ajie.community.mapper.UserMapper;
import life.ajie.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO=new PaginationDTO<>();
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        Integer totalCount = (int)notificationMapper.countByExample(notificationExample);

        Integer totalPage;
        if(totalCount%size==0)
            totalPage=totalCount/size;
        else
            totalPage=totalCount/size+1;
        if(page<1)
            page=1;
        if(page>totalPage)
            page=totalPage;
        paginationDTO.setPagination(totalPage,page);
        Integer offset=size*(page-1);
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmtCreate desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));

        if(notifications.size()==0){
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(long id, User user) {

        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification.getReceiver()!=user.getId()){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        if(notification==null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());

        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setOuterId(notification.getOuterid());
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
