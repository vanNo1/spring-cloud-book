package van.bookbookprovider.controller;



import base.Const;
import base.ServerResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.LoginUtil;
import van.bookbookprovider.service.serviceImpl.BookServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Van
 * @date 2020/3/18 - 10:37
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private BookServiceImpl bookService;

    @RequestMapping(value = {"/recommend/v2", "freeRead"})
    public ServerResponse recommend() {
        return bookService.recomment();
    }
    @RequestMapping("/recommend.v2")
    public ServerResponse recommend2(HttpSession session){
        String openId=(String) session.getAttribute(Const.CURRENT_USER);
        return bookService.recommendV2(openId);
    }

    @RequestMapping("/hotBook/v2")
    public ServerResponse hotBook() {
        return bookService.hotBook();
    }

    @RequestMapping("/v2")
    public ServerResponse home(HttpSession session) {
        //QPS:19.6 200线程跑10次 (无任何优化)
        //QPS:36.7 20线程跑1次 启动2s 启动如果为0 程序会崩，后面就访问不了了（把category加入了redis）
        String openId = LoginUtil.getOpenId(session);
        return bookService.getHomeData(openId);

    }

}
