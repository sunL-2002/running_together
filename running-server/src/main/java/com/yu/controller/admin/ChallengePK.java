package com.yu.controller.admin;

import com.yu.dto.ChallengePageQueryDTO;
import com.yu.result.PageResult;
import com.yu.result.Result;
import com.yu.service.ChallengesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/ChallengePK")
@Slf4j
@Api(tags = "挑战相关接口")
public class ChallengePK {

    @Autowired
    private ChallengesService challengesService;

    @GetMapping("/page")
    @ApiOperation("挑战分页查询")
    public Result<PageResult> page(ChallengePageQueryDTO challengePageQueryDTO){
        System.out.println("收到一条PK请求");
        PageResult pageResult = challengesService.pageQuery(challengePageQueryDTO);

        return Result.success(pageResult);
    }
}
