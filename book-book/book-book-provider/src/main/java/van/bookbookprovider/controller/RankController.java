package van.bookbookprovider.controller;

import api.rank.RankAPI;
import base.Const;
import base.ServerResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import van.bookbookprovider.service.serviceImpl.RankServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Van
 * @date 2020/3/17 - 13:13
 */
@Validated
@RestController
@RequestMapping("/rank")
public class RankController implements RankAPI {
    @Resource
    private RankServiceImpl rankService;

    @Override
    public ServerResponse save(@NotEmpty String fileName, @NotNull Integer rank, HttpSession session) {
        //need login
        String openId = (String) session.getAttribute(Const.CURRENT_USER);
        return rankService.save(fileName, rank, openId);
    }
}
