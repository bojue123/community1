package life.ajie.community.mapper;

import life.ajie.community.dto.QuestionDTO;
import life.ajie.community.dto.QuestionQueryDTO;
import life.ajie.community.model.Question;
import life.ajie.community.model.QuestionExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface QuestionMapper {
//    @Insert("insert into question (title,description,gmtCreate,gmtModified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
//    public void create(Question question);
//
//    @Select("select * from question limit #{offset},#{size}")
//    List<Question> list(@Param(value="offset") Integer offset, @Param(value="size")Integer size);
//
//    @Select("select count(1) from question")
//    Integer count();
//
//    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
//    List<Question> listByUserId(@Param(value="userId")long userId,@Param(value="offset") Integer offset, @Param(value="size")Integer size);
//
//    @Select("select count(1) from question where creator = #{userId}")
//    Integer countByUserId(@Param(value="userId")long userId);
//
//    @Select("select * from question where id=#{id}")
//    public Question selectById(@Param("id")long id);
//
//    @Update("update question set title = #{title},description=#{description},gmtModified=#{gmtModified},tag=#{tag},viewCount=#{viewCount},commentCount=#{commentCount} where id=#{id}")
//    int update(Question question);

    long countByExample(QuestionExample example);

    int deleteByExample(QuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    int insertSelective(Question record);

    List<Question> selectByExampleWithBLOBsWithRowbounds(QuestionExample example, RowBounds rowBounds);

    List<Question> selectByExampleWithBLOBs(QuestionExample example);

    List<Question> selectByExampleWithRowbounds(QuestionExample example, RowBounds rowBounds);

    List<Question> selectByExample(QuestionExample example);

    Question selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExampleWithBLOBs(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKeyWithBLOBs(Question record);

    int updateByPrimaryKey(Question record);

    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}
