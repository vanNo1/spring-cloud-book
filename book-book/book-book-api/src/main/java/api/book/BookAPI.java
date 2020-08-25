package api.book;

import base.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

@FeignClient(name = "book", fallback = BookFallback.class)
public interface BookAPI {
    @RequestMapping("/introduction")
    ServerResponse introduction(@NotEmpty String fileName);

    @RequestMapping("/detail.do")
    ServerResponse detail(String fileName, String openId);

    @RequestMapping("/v2/detail.do")
    ServerResponse detail2(@NotEmpty String fileName);

    @RequestMapping("/search")
    ServerResponse search(HttpSession session, @NotEmpty String keyword, @RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "20") int pageSize);

    @RequestMapping("/search-list")
    ServerResponse searchList(String publisher, String author, String category, Integer categoryId,
                              @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int pageSize);

    @RequestMapping("/hot-search")
    ServerResponse hotSearch();


}
