package van.bookbookprovider.service.serviceImpl;

import base.CodeMsg;
import base.ServerResponse;
import exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import van.bookbookprovider.dao.ContentMapper;
import van.bookbookprovider.entity.Contents;
import van.bookbookprovider.service.ContentService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Van
 * @date 2020/3/18 - 10:15
 */
@Service
@Slf4j
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentMapper contentMapper;

    public ServerResponse content(String fileName) {
        Map map = new HashMap();
        map.put("file_name", fileName);
        List<Contents> contentsList = contentMapper.selectByMap(map);
        if (contentsList.size() == 0) {
            throw new GlobalException(CodeMsg.BOOK_NOT_EXIST);
        }
        return ServerResponse.success("查询成功", contentsList);
    }
}
