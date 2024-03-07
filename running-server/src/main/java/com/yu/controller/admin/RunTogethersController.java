package com.yu.controller.admin;


import com.yu.dto.RunTogetherPageQueryDTO;
import com.yu.result.PageResult;
import com.yu.result.Result;
import com.yu.service.RunTogetherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/runTogether")
@Slf4j
@Api(tags = "约跑相关接口")
public class RunTogethersController {

    @Autowired
    private RunTogetherService runTogetherService;

    @GetMapping("/page")
    @ApiOperation("约跑分页查询")
    public Result<PageResult> page(RunTogetherPageQueryDTO runTogetherPageQueryDTO){
        System.out.println("收到一条约跑请求");
        PageResult pageResult = runTogetherService.pageQuery(runTogetherPageQueryDTO);
        System.out.println("==="+pageResult);
        return Result.success(pageResult);
    }
}
