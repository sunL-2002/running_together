package com.yu.controller.admin;

import com.yu.dto.GroupPageQueryDTO;
import com.yu.result.PageResult;
import com.yu.result.Result;
import com.yu.service.OrganizationService;
import com.yu.vo.PageMemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 跑团管理
 */
@RestController
@RequestMapping("/admin/Group")
@Slf4j
@Api(tags = "跑团相关接口")
public class GroupController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("{id}")
    public Result<List<PageMemberVO>> pageMember(@PathVariable int id){
        System.out.println("收到一条跑团成员请求");
        List<PageMemberVO> pageMemberVOS = organizationService.pageMember(id);
        return Result.success(pageMemberVOS);
    }

    @GetMapping("/page")
    @ApiOperation("跑团分页查询")
    public Result<PageResult> page(GroupPageQueryDTO groupPageQueryDTO){
        PageResult pageResult = organizationService.pageQuery(groupPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("跑团批量删除")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除了："+ids);
        organizationService.deleteBach(ids);

        //将所有的菜品缓存数据清理掉，所有的以dish_开头的key
        clearCache("organizations*");

        return Result.success();
    }

    /**
     * 清理缓存数据
     * @param pattern
     */
    private void clearCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
