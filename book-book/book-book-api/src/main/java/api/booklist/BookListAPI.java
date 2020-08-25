package api.booklist;

import base.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import van.bookbookprovider.entity.BookList;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
@FeignClient(name = "bookList", fallback = BookListFallback.class)
public interface BookListAPI {
    @RequestMapping("/deleteBookList")
    ServerResponse deleteBookList(@NotEmpty String bookList, HttpSession session);

    @RequestMapping("/showBookList")
    ServerResponse showBookList(@NotEmpty String bookList, @RequestParam(defaultValue = "1") int current,
                                @RequestParam(defaultValue = "6") int pageSize);

    @RequestMapping("/deleteBook")
    ServerResponse deleteBook(HttpSession session, @NotEmpty String bookList, @NotEmpty String fileName);

    @RequestMapping("/addBook")
    ServerResponse addBook(HttpSession session, @NotEmpty String bookList, @NotEmpty String fileName);

    @RequestMapping("/likeOrDislike")
    ServerResponse likeOrDislike(HttpSession session, @NotEmpty String bookList);

    @RequestMapping("/create")
    ServerResponse create(HttpSession session, @Validated BookList bookList);


}
