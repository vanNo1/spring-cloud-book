package api.banner;

import base.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "banner", fallback = BannerFallback.class)
public interface BannerAPI {
    @RequestMapping("/list")
    ServerResponse list();
}
