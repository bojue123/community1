package life.ajie.community.mapper;

import life.ajie.community.model.User;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;

@Mapper
public interface   UserMapper {
    @Insert("insert into user (name,accountId,token,gmtCreate,gmtModified,avatarUrl) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);
    @Select("select * from user where id=#{id}")
    User findById(@Param("id")Integer id);
    @Select("select * from user where accountId=#{accountId}")
    User findByAccountId(@Param("accountId")String accountId);
    @Update("update user set name=#{name},token=#{token},gmtModified=#{gmtModified},avatarUrl=#{avatarUrl} where id=#{id}")
    void update(User user);
}
