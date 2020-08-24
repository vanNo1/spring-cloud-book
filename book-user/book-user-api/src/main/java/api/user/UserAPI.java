package api.user;

import base.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import van.bookuserprovider.dto.ChangePasswordVO;
import van.bookuserprovider.dto.LoginDTO;
import van.bookuserprovider.entity.User;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

@FeignClient(name = "user", fallback = UserFallback.class)
public interface UserAPI {
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ServerResponse register(@Validated User user);

    @RequestMapping("/day")
    ServerResponse getDay(String openId);

    @RequestMapping("/attendance")
    ServerResponse attendance(HttpSession session);

    @RequestMapping("/login.do")
    ServerResponse login(@Validated LoginDTO loginDTO, HttpSession session);

    @RequestMapping("/logout.do")
    ServerResponse logout(@NotEmpty String openId, HttpSession session);

    @RequestMapping("/forget_password.do")
    ServerResponse forgetPassword(@NotEmpty String openId, @NotEmpty String answer);

    @RequestMapping("/forget_reset_password.do")
    ServerResponse forgetRestPassword(@NotEmpty String openId, @NotEmpty String newPassword);

    @RequestMapping("/change_password.do")
    ServerResponse changePassword(@Validated ChangePasswordVO vo);


}
