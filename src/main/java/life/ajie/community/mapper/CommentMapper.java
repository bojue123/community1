package life.ajie.community.mapper;

import life.ajie.community.model.Comment;
import life.ajie.community.model.CommentExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
//

public interface CommentMapper {
//    @Insert("insert into comment (parentId,type,commentator,gmtCreate,gmtModified,likeCount,content) values (#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
//    public void insert(Comment comment);
//    @Select("select * from comment where parentId=#{parentId}")
//    public Comment selectById(@Param("parentId")long parentId);

    int incCommentCount(Comment comment);

    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExampleWithRowbounds(CommentExample example, RowBounds rowBounds);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}
