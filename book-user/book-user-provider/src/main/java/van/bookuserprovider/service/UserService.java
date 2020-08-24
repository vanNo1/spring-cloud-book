package van.bookuserprovider.service;

import base.*;
import van.bookuserprovider.entity.User;

/**
 * @author Van
 * @date 2020/3/7 - 14:31
 */
public interface UserService {
    ServerResponse register(User user);

    ServerResponse getDay(String openId);

    User selectUserByOpenId(String openId);

    boolean isDupilicate(String openId);

    ServerResponse changePassword(String username, String oldPassword, String newPassword, String openId);

    ServerResponse forgetPassword(String openId, String answer);

    ServerResponse forgetPasswordAndChangePassword(String openId, String forgetToken, String newPassword);

    ServerResponse login(String openId, String username, String password);
}
