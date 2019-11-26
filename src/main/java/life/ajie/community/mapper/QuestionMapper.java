package life.ajie.community.mapper;

import life.ajie.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmtCreate,gmtModified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value="offset") Integer offset, @Param(value="size")Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value="userId")Integer userId,@Param(value="offset") Integer offset, @Param(value="size")Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param(value="userId")Integer userId);
}
