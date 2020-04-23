package life.ajie.community.Exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"您找的问题不存在，换一个试试吧"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题和评论"),
    NO_LOGIN(2003,"未登录不能评论，请先登录"),
    SYS_ERROR(2004,"服务冒烟了，请稍后再试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"您操作的评论不存在，换一个试试吧"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"此信息不是你的"),
    NOTIFICATION_NOT_FOUND(2009,"查询的通知信息不存在"),
    ;
    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message){
        this.code=code;
        this.message=message;
    }
}
