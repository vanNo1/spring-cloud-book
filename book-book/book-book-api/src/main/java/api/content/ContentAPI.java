package api.content;

import base.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
@FeignClient(name = "content", fallback = ContentFallback.class)
public interface ContentAPI {
    @RequestMapping("/contents")
    ServerResponse content(@NotEmpty String fileName, HttpSession session);

}
