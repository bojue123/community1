package life.ajie.community.service;

import life.ajie.community.mapper.UserMapper;
import life.ajie.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser=userMapper.findByAccountId(user.getAccountId());
        if(dbUser==null){
            //插入

            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified((user.getGmtCreate()));
            userMapper.insert(user);
        }else {
            //更新
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }
    }
}
