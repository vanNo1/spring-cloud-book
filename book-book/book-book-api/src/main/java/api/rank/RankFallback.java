package api.rank;

import base.CodeMsg;
import base.ServerResponse;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RankFallback implements RankAPI {
    @Override
    public ServerResponse save(@NotEmpty String fileName, @NotNull Integer rank, HttpSession session) {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }
}
