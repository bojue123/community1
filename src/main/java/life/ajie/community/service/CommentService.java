package life.ajie.community.service;

import life.ajie.community.Exception.CustomizeErrorCode;
import life.ajie.community.Exception.CustomizeException;
import life.ajie.community.dto.CommentDTO;
import life.ajie.community.enums.CommentTypeEnum;
import life.ajie.community.enums.NotificationStatusEnum;
import life.ajie.community.enums.NotificationTypeEnum;
import life.ajie.community.mapper.CommentMapper;
import life.ajie.community.mapper.NotificationMapper;
import life.ajie.community.mapper.QuestionMapper;
import life.ajie.community.mapper.UserMapper;
import life.ajie.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private NotificationMapper notificationMapper;
    public void insert(Comment comment, User commentator) {
        if(comment.getParentId()==null){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);

        }
        if(CommentTypeEnum.isExist(comment.getType())){
        }else{
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);

        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment=commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert((comment));

            //回复问题
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

            //增加评论数
            Comment  parentcomment = new Comment();
            parentcomment.setId(comment.getParentId());
            parentcomment.setCommentCount(1);
            commentMapper.incCommentCount(parentcomment);

            //创建通知
            createNotify(comment, dbComment.getCommentator(),commentator.getName(),question.getTitle(), NotificationTypeEnum.REPLY_COMMENT,question.getId());

        }else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionMapper.incCommentCount(question);

            //创建通知
            createNotify(comment,question.getCreator(), commentator.getName(),question.getTitle(), NotificationTypeEnum.REPLY_QUESTION,question.getId());

        }
    }

    private void createNotify(Comment comment, long receiver, String notifierName, String outTile, NotificationTypeEnum notificationType, Long outerId) {
        if(receiver == comment.getCommentator()){
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outTile);
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {

        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmtCreate desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size()==0){
            return new ArrayList<>();
        }
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds=new ArrayList();
        userIds.addAll(commentators);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);

        List<User> users = userMapper.selectByExample(userExample);
        Map<Long,User> userMap = users.stream().collect(Collectors.toMap(user->user.getId(), user->user));

        List<CommentDTO> commentDTOS=comments.stream().map(comment -> {
            CommentDTO commentDTO=new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
