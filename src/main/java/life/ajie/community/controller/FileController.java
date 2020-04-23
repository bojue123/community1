package life.ajie.community.controller;

import life.ajie.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {
    @RequestMapping("/file/upload")
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/ajie.jpg");
        return fileDTO;
    }

}
