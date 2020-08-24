package van.bookbookprovider.controller;


import base.ServerResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.LoginUtil;
import van.bookbookprovider.service.serviceImpl.BookServiceImpl;
import van.bookbookprovider.service.serviceImpl.HotBookServiceImpl;
import van.bookbookprovider.service.serviceImpl.HotSearchServiceImpl;
import van.bookbookprovider.service.serviceImpl.IntroductionServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

/**
 * @author Van
 * @date 2020/3/17 - 16:02
 */
@RestController
@Validated
public class BookController {
    @Resource
    private IntroductionServiceImpl introductionService;
    @Resource
    private HotBookServiceImpl hotBookService;
    @Resource
    private BookServiceImpl bookService;
    @Resource
    private HotSearchServiceImpl hotSearchService;
    @RequestMapping("/introduction")
    public ServerResponse introduction(@NotEmpty String fileName){
        return introductionService.getIntroduction(fileName);
    }

    @RequestMapping("/detail.do")
    public ServerResponse detail(String fileName, String openId) {

        return bookService.getDetail(openId, fileName);
    }
    @RequestMapping("/v2/detail.do")
    public ServerResponse detail2(@NotEmpty String fileName){
        return bookService.selectBookDetailV2(fileName);
    }

    //搜索
    @RequestMapping("/search")
    public ServerResponse search(HttpSession session, @NotEmpty String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int pageSize) {
        //is user is login then insert keyword to databse;
        if (LoginUtil.isLogin(session)) {
            hotSearchService.insert(keyword, LoginUtil.getOpenId(session));
        }
        return bookService.search(keyword, page, pageSize);
    }

    @RequestMapping("/search-list")
    public ServerResponse searchList(String publisher, String author, String category, Integer categoryId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int pageSize) {
        if (publisher == null && author == null && category == null && categoryId == null) {
            return ServerResponse.error("参数有误");
        }
        return bookService.searchList(publisher, category, categoryId, author,page,pageSize);
    }

    @RequestMapping("/hot-search")
    public ServerResponse hotSearch() {

        return hotBookService.hotSearch();
    }

}
