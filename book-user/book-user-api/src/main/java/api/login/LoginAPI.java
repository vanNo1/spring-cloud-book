package api.login;

import base.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

@FeignClient(name = "login", fallback = LoginFallback.class)
public interface LoginAPI {
    @RequestMapping("/get")
    ServerResponse login(@NotEmpty String code, @NotEmpty String appId, @NotEmpty String secret, HttpSession session);

}
