package api.category;

import base.CodeMsg;
import base.ServerResponse;

public class CategoryFallback implements CategoryAPI{
    @Override
    public ServerResponse list() {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);

    }
}
