package utils;

import base.Const;

import javax.servlet.http.HttpSession;

/**
 * @author Van
 * @date 2020/3/17 - 12:13
 */
public class LoginUtil {
    public static boolean isLogin(HttpSession session) {
        String  openId = (String) session.getAttribute(Const.CURRENT_USER);
        if (openId == null) {
            return false;
        } else {
            return true;
        }

    }

    public static String getOpenId(HttpSession session) {
        String openId = (String) session.getAttribute(Const.CURRENT_USER);
        if (openId == null) {
            return null;
        } else {
            return openId;
        }
    }

}
