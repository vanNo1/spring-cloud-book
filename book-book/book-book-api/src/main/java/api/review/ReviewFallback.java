package api.review;

import base.CodeMsg;
import base.ServerResponse;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ReviewFallback implements ReviewAPI {
    @Override
    public ServerResponse insert(HttpSession session, @NotEmpty String fileName, @Size(min = 10) String summary, @NotEmpty String title) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse list(@NotEmpty String fileName, int pageSize, int current) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse delete(HttpSession session, @NotEmpty String fileName) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }
}
