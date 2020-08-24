package van.bookuserprovider.controller;


import api.user.UserAPI;
import base.CodeMsg;
import base.Const;
import base.ServerResponse;
import exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import van.bookuserprovider.dto.ChangePasswordVO;
import van.bookuserprovider.dto.LoginDTO;
import van.bookuserprovider.entity.User;
import van.bookuserprovider.service.UserService;
import van.bookuserprovider.service.serviceImpl.DailyAttendanceServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

/**
 * @author Van
 * @date 2020/3/7 - 14:28
 */
@Validated
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController implements UserAPI {
    @Resource
    private UserService userService;
    @Resource
    private DailyAttendanceServiceImpl dailyAttendanceService;

    @Override
    public ServerResponse register(@Validated User user) {
        return userService.register(user);
    }

    @Override
    public ServerResponse getDay(String openId) {
        return userService.getDay(openId);
    }

    @Override
    public ServerResponse attendance(HttpSession session) {
        String openId = (String) session.getAttribute(Const.CURRENT_USER);
        return dailyAttendanceService.attendance(openId);
    }

    @Override
    public ServerResponse login(@Validated LoginDTO loginDTO, HttpSession session) {
        String openId = loginDTO.getOpenId();
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        ServerResponse serverResponse = userService.login(openId, username, password);
        //only success will get here because error is already throw Exception
        session.setAttribute(Const.CURRENT_USER, openId);
        return serverResponse;
    }

    @Override
    public ServerResponse logout(@NotEmpty String openId, HttpSession session) {
        String result = (String) session.getAttribute(openId);
        if (result == null) {
            throw new GlobalException(CodeMsg.NOT_LOGIN);
        }
        session.removeAttribute(openId);
        return ServerResponse.success("用户退出成功");
    }

    @Override
    public ServerResponse forgetPassword(@NotEmpty String openId, @NotEmpty String answer) {
        return userService.forgetPassword(openId, answer);
    }

    @Override
    public ServerResponse forgetRestPassword(@NotEmpty String openId, @NotEmpty String newPassword) {
        String token = Const.TOKEN_PREFIX + openId;

        return userService.forgetPasswordAndChangePassword(openId, token, newPassword);
    }

    @Override
    public ServerResponse changePassword(@Validated ChangePasswordVO vo) {
        if (vo.getNewPassword().equals(vo.getOldPassword())) {
            throw new GlobalException(CodeMsg.SAME_PASSWORD);
        }
        return userService.changePassword(vo.getUsername(), vo.getOldPassword(), vo.getNewPassword(), vo.getOpenId());
    }
}
