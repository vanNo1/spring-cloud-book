package api.banner;

import base.CodeMsg;
import base.ServerResponse;
import org.springframework.stereotype.Component;

@Component
public class BannerFallback implements BannerAPI {
    @Override
    public ServerResponse list() {
        return ServerResponse.error(CodeMsg.CIRCUIT_BREAKER);
    }
}
