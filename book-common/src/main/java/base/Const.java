package base;

/**
 * @author Van
 * @date 2020/3/15 - 11:56
 */
public class Const {
    public static final String CACHE_CATEGORY="categoryCache";
    public static final String TOKEN_PREFIX="token_";
    public static final String CURRENT_USER = "CURRENT_USER";
    public static final String DOMAIN = "http://store.yangxiansheng.top/";
    public static final String DOMAIN_IMG="http://store.yangxiansheng.top/img";
    public static final Integer MAXBOOKS = 400;
    public static final Integer DUPLICATE = 11;//some key is duplicate
    public static final Integer ISNULL = 5;//some param is null
    public static final Integer SYSTEMERROR = -1;//system error
    public static final String  WAIT_TO_ADD_INTRODUCTION="待添加记录....";

    public interface ServerResponse {
        public static final int SUCCESS_CODE = 0;
        public static final int ERROR_CODE = -1;
    }

}
