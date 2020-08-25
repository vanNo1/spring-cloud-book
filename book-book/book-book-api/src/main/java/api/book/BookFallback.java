package api.book;

import base.CodeMsg;
import base.ServerResponse;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

public class BookFallback implements BookAPI {
    @Override
    public ServerResponse introduction(@NotEmpty String fileName) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);
    }

    @Override
    public ServerResponse detail(String fileName, String openId) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse detail2(@NotEmpty String fileName) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse search(HttpSession session, @NotEmpty String keyword, int page, int pageSize) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse searchList(String publisher, String author, String category, Integer categoryId, int page, int pageSize) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse hotSearch() {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }
}
