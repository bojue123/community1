package life.ajie.community.cache;

import com.sun.java.swing.plaf.windows.resources.windows;
import life.ajie.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get(){
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO fore = new TagDTO();
        fore.setCategoryName("前端");
        fore.setTags(Arrays.asList("javascript","vue.js","css","html","html5","node.js","react.js",
                "jquery","css3","es6","typescript","chrome","npm","bootstrap","sass","less",
                "chrome-devtools","firefox","angular","coffeescript","safari","postman","postcss","fiddler",
                "yarn","webkit","firebug","edge"
        ));
        tagDTOS.add(fore);

        TagDTO back = new TagDTO();
        back.setCategoryName("后端");
        back.setTags(Arrays.asList("php","java","node.js","python","C++","C","go","lang","spring",
                "springboot","django","flask","c#","swoole","ruby","asp.net","ruby-on-rails","scala",
                "rust","lavarel","爬虫"
        ));
        tagDTOS.add(back);

        TagDTO mobile = new TagDTO();
        mobile.setCategoryName("移动端");
        mobile.setTags(Arrays.asList("java","android","ios","objective-c","小程序","swift","react-native",
                "xcode","android-studio","webapp","flutter","kotlin"
        ));
        tagDTOS.add(mobile);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","redis","mongodb","sql","json","elasticsearch","nosql","memcached",
                "postgresql","sqlite","mariadb"
        ));
        tagDTOS.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("工具");
        tool.setTags(Arrays.asList("git","github","macos","visual-studio-code","windows","vim","sublime-text",
                "intellij-idea","phpstorm", "eclipse","webstorm","svn","visual-studio","pycharm","emacs"
        ));
        tagDTOS.add(tool);

        TagDTO devops = new TagDTO();
        devops.setCategoryName("运维");
        devops.setTags(Arrays.asList("linux","nginx","docker","apache","centos","ubuntu","服务器","负载均衡",
                "ssh","vagrant","容器","jenkins","devops","debian","fabric"
        ));
        tagDTOS.add(devops);



        return tagDTOS;
    }

    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }

}
