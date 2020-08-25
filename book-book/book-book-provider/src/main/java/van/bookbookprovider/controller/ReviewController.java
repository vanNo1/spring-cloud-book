package van.bookbookprovider.controller;


import api.review.ReviewAPI;
import base.Const;
import base.ServerResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import van.bookbookprovider.service.serviceImpl.ReviewServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Van
 * @date 2020/4/4 - 11:42
 */
@Validated
@RestController
@RequestMapping("/review")
public class ReviewController implements ReviewAPI {
    @Resource
    private ReviewServiceImpl reviewService;

    @Override
    public ServerResponse insert(HttpSession session, @NotEmpty String fileName, @Size(min = 10) String summary, @NotEmpty String title) {
        //need to login
        String openId = (String) session.getAttribute(Const.CURRENT_USER);
        return reviewService.insertOrUpdateReview(openId, fileName, summary, title);
    }

    @Override

    public ServerResponse list(@NotEmpty String fileName, @RequestParam(defaultValue = "3") int pageSize,
                               @RequestParam(defaultValue = "1") int current) {
        return reviewService.listReview(fileName, pageSize, current);
    }

    @Override

    public ServerResponse delete(HttpSession session, @NotEmpty String fileName) {
        //need to login
        String openId = (String) session.getAttribute(Const.CURRENT_USER);
        return reviewService.deleteReview(openId, fileName);
    }
}
