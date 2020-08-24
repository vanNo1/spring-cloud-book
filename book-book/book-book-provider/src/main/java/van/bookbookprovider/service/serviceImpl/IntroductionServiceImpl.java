package van.bookbookprovider.service.serviceImpl;

import base.CodeMsg;
import base.Const;
import base.ServerResponse;
import exception.GlobalException;
import org.springframework.stereotype.Service;
import van.bookbookprovider.dao.IntroductionMapper;
import van.bookbookprovider.entity.Book;
import van.bookbookprovider.entity.Introduction;
import van.bookbookprovider.service.IntroductionService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Van
 * @date 2020/4/4 - 18:23
 */
@Service
public class IntroductionServiceImpl implements IntroductionService {
    @Resource
    private IntroductionMapper introductionMapper;
    @Resource
    private BookServiceImpl bookService;

    public ServerResponse<Introduction> getIntroduction(String fileName) {
        Introduction introduction = new Introduction();
        Map map = new HashMap();
        map.put("file_name", fileName);
        Book book = bookService.selectBookByFileName(fileName);
        //if book is not exist
        if (book == null) {
            throw new GlobalException(CodeMsg.BOOK_NOT_EXIST);
        }
        //if the introduction of this book is not in DB
        List<Introduction> introductions = introductionMapper.selectByMap(map);
        if (introductions.size() == 0) {
            introduction.setAuthor(Const.WAIT_TO_ADD_INTRODUCTION);
            introduction.setContent(Const.WAIT_TO_ADD_INTRODUCTION);
            introduction.setFileName(fileName);
            return ServerResponse.success("待添加", introduction);
        }
        //book's introduction is in DB,return introduction
        introduction = introductions.get(0);
        return ServerResponse.success("查找成功", introduction);
    }
}
