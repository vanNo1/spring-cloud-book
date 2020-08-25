package api.review;

import base.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@FeignClient(name = "review", fallback = ReviewFallback.class)
public interface ReviewAPI {
    @RequestMapping("/insertOrUpdate")
    ServerResponse insert(HttpSession session, @NotEmpty String fileName, @Size(min = 10) String summary, @NotEmpty String title);

    @RequestMapping("/list")
    ServerResponse list(@NotEmpty String fileName, @RequestParam(defaultValue = "3") int pageSize,
                        @RequestParam(defaultValue = "1") int current);

    @RequestMapping("/delete")
    ServerResponse delete(HttpSession session, @NotEmpty String fileName);
}
