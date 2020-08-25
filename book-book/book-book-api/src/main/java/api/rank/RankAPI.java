package api.rank;

import base.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@FeignClient(name = "rank", fallback = RankFallback.class)
public interface RankAPI {
    @RequestMapping("/save")
    ServerResponse save(@NotEmpty String fileName, @NotNull Integer rank, HttpSession session);
}
