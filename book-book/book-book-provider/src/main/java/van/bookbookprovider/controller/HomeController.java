package van.bookbookprovider.controller;


import api.home.HomeAPI;
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
public class HomeController implements HomeAPI {

    @Resource
    private BookServiceImpl bookService;

    @Override
    public ServerResponse recommend() {
        return bookService.recomment();
    }

    @Override

    public ServerResponse recommend2(HttpSession session) {
        String openId = (String) session.getAttribute(Const.CURRENT_USER);
        return bookService.recommendV2(openId);
    }

    @Override

    public ServerResponse hotBook() {
        return bookService.hotBook();
    }

    @Override

    public ServerResponse home(HttpSession session) {
        //QPS:19.6 200线程跑10次 (无任何优化)
        //QPS:36.7 20线程跑1次 启动2s 启动如果为0 程序会崩，后面就访问不了了（把category加入了redis）
        String openId = LoginUtil.getOpenId(session);
        return bookService.getHomeData(openId);

    }

}
