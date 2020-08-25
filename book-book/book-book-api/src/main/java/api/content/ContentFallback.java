package api.content;

import base.CodeMsg;
import base.ServerResponse;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

public class ContentFallback implements ContentAPI {
    @Override
    public ServerResponse content(@NotEmpty String fileName, HttpSession session) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }
}
