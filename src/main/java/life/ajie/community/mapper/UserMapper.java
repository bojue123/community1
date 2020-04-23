package life.ajie.community.mapper;

import life.ajie.community.model.User;
import life.ajie.community.model.UserExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import javax.annotation.Resource;
import java.util.List;

public interface   UserMapper {
//    @Insert("insert into user (name,accountId,token,gmtCreate,gmtModified,avatarUrl) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
//    void insert(User user);
//    @Select("select * from user where token=#{token}")
//    User findByToken(@Param("token") String token);
//    @Select("select * from user where id=#{id}")
//    User findById(@Param("id")long id);
//    @Select("select * from user where accountId=#{accountId}")
//    User findByAccountId(@Param("accountId")String accountId);
//    @Update("update user set name=#{name},token=#{token},gmtModified=#{gmtModified},avatarUrl=#{avatarUrl} where id=#{id}")
//    void update(User user);

    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExampleWithRowbounds(UserExample example, RowBounds rowBounds);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
