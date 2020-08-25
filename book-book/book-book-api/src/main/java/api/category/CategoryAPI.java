package api.category;

import base.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
@FeignClient(name = "category", fallback = CategoryFallback.class)
public interface CategoryAPI {
    @RequestMapping("/list.do")
    ServerResponse list();

}
