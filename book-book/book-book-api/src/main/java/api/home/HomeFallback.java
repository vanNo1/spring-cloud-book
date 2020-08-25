package api.home;

import base.CodeMsg;
import base.ServerResponse;

import javax.servlet.http.HttpSession;

public class HomeFallback implements HomeAPI {
    @Override
    public ServerResponse recommend() {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse recommend2(HttpSession session) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse hotBook() {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }

    @Override
    public ServerResponse home(HttpSession session) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }
}
