package life.ajie.community.service;

import life.ajie.community.dto.PaginationDTO;
import life.ajie.community.dto.QuestionDTO;
import life.ajie.community.mapper.QuestionMapper;
import life.ajie.community.mapper.UserMapper;
import life.ajie.community.model.Question;
import life.ajie.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {


        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();

        Integer totalPage;
        if(totalCount%size==0)
            totalPage=totalCount/size;
        else
            totalPage=totalCount/size+1;
        if(page<1)
            page=1;
        if(page>totalPage)
            page=totalPage;
        paginationDTO.setPagination(totalPage,page);
        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            //可以快速把两个对象相同属性的值进行赋值，不一样的不处理
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        Integer totalPage;
        if(totalCount==0){
            return new PaginationDTO();
        }
        if(totalCount%size==0)
            totalPage=totalCount/size;
        else
            totalPage=totalCount/size+1;
        if(page<1)
            page=1;
        if(page>totalPage)
            page=totalPage;
        paginationDTO.setPagination(totalPage,page);
        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            //可以快速把两个对象相同属性的值进行赋值，不一样的不处理
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }
}
