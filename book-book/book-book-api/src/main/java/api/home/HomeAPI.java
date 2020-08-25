package api.home;

import base.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@FeignClient(name = "home", fallback = HomeFallback.class)
public interface HomeAPI {
    @RequestMapping(value = {"/recommend/v2", "freeRead"})
    ServerResponse recommend();

    @RequestMapping("/recommend.v2")
    ServerResponse recommend2(HttpSession session);

    @RequestMapping("/hotBook/v2")
    ServerResponse hotBook();

    @RequestMapping("/v2")
    ServerResponse home(HttpSession session);


}
