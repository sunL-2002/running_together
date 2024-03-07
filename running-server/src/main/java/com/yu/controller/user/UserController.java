package com.yu.controller.user;

import com.yu.constant.JwtClaimsConstant;
import com.yu.dto.UserLoginDTO;
import com.yu.entity.User;
import com.yu.properties.JwtProperties;
import com.yu.result.Result;
import com.yu.service.TokenValidator;
import com.yu.service.UserService;
import com.yu.utils.JwtUtil;
import com.yu.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/users")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/{openid}")
    public Result<User> getUserInfo(@PathVariable String openid){
        User userInfo = userService.getUserInfo(openid);
        return Result.success(userInfo);
    }
    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    @PostMapping("/auth")
    public boolean TokenValidator(String token){
        return tokenValidator.isTokenExpired(token);
    }

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("微信登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("微信用户登录：{}",userLoginDTO.getCode());

        //微信登录
        User user = userService.wxLogin(userLoginDTO);

        //为微信用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getUserId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getUserTtl(),claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getUserId())
                .openid(user.getOpenid())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }

}
