package api.booklist;

import base.CodeMsg;
import base.ServerResponse;
import van.bookbookprovider.entity.BookList;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

public class BookListFallback implements BookListAPI {
    @Override
    public ServerResponse deleteBookList(@NotEmpty String bookList, HttpSession session) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse showBookList(@NotEmpty String bookList, int current, int pageSize) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse deleteBook(HttpSession session, @NotEmpty String bookList, @NotEmpty String fileName) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse addBook(HttpSession session, @NotEmpty String bookList, @NotEmpty String fileName) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse likeOrDislike(HttpSession session, @NotEmpty String bookList) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse create(HttpSession session, BookList bookList) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }
}
