package com.yu.service;

public interface TokenValidator {

    /**
     * 验证token是否过期
     * @param token 待验证的token
     * @return 如果过期返回true，否则返回false
     */
    boolean isTokenExpired(String token);
}
