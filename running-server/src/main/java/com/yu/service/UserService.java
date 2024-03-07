package com.yu.service;

import com.yu.dto.UserLoginDTO;
import com.yu.entity.User;

public interface UserService {

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);

    User getUserInfo(String openid);
}
