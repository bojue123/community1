package life.ajie.community.provider;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import life.ajie.community.dto.AccessTokenDTO;
import life.ajie.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        //将得到的信息类转成正确格式，再给API，得到信息返回



        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        //创建一个访问用户
        OkHttpClient client = new OkHttpClient();
        //将信息打包成特定json格式
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            //ctrl+alt+v快速添加变量

            return githubUser;
        } catch (IOException e) {

        }
        return null;

    }



}
