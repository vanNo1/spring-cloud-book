package exception;

import base.CodeMsg;
import lombok.Getter;

/**
 * @author Van
 * @date 2020/3/31 - 17:08
 */
@Getter
public class GlobalException extends RuntimeException {
    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }
}
