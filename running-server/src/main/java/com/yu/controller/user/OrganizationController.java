package com.yu.controller.user;

import com.yu.dto.GetInfoByTagDTO;
import com.yu.dto.InsertGroupMemberDTO;
import com.yu.dto.RunningGroupDTO;
import com.yu.entity.User;
import com.yu.result.Result;
import com.yu.service.OrganizationService;
import com.yu.vo.OrganizationInfoVO;
import com.yu.vo.OrganizationsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 组织管理
 */
@RestController
@RequestMapping("/user/organizations")
@Slf4j
@Api(tags = "组织相关接口")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/add")
    public Result addGroup(@RequestBody InsertGroupMemberDTO insertGroupMemberDTO){
        int i = organizationService.addGroupMember(insertGroupMemberDTO);
        if (i == 0){
            return Result.error("用户已加入该组织");
        }
        return Result.success();
    }

    @GetMapping("/member/{id}")
    public Result<List<User>> getMemberByGroupId(@PathVariable int id){
        System.out.println("查询跑团【"+id+"】成员");
        List<User> memberByGroupId = organizationService.getMemberByGroupId(id);
        return Result.success(memberByGroupId);
    }

    @GetMapping("/{id}")
    public Result<OrganizationInfoVO> getOrganizationInfo(@PathVariable int id){
        System.out.println("查询了Organization【"+id+"】");
        OrganizationInfoVO organizationInfo = organizationService.getOrganizationInfo(id);
        System.out.println(organizationInfo);
        return Result.success(organizationInfo);
    }

    @GetMapping("/getOrganization2")
    @ApiOperation("返回组织信息")
    public Result<List<OrganizationsVO>> getOrganization2(){
        //创建Redis的key
        String key = "organization";

        //缓存中是否存在
        List<OrganizationsVO> list = (List<OrganizationsVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size()>0){
            //如果存在，直接返回，无须查询数据库
            return Result.success(list);
        }
        //如果不存在，查询数据库，将查询到的数据放入rides中

        list = organizationService.getOrganization2();
        redisTemplate.opsForValue().set(key,list);

        return Result.success(list);
    }

    /**
     * 创建组织
     * @param runningGroupDTO
     * @return
     */
    @PostMapping("/saveOrganization")
    @ApiOperation("添加组织信息")
    public Result save(@RequestBody RunningGroupDTO runningGroupDTO){

        log.info("创建组织: {}",runningGroupDTO);
        organizationService.saveOrganization(runningGroupDTO);

        //清除缓存数据
        clearCache("organizations*");

        return Result.success();
    }

    /**
     * 查询组织
     * @return
     */
    @GetMapping("/getOrganizations")
    @ApiOperation("返回全国组织信息")
    public Result<List<OrganizationsVO>> getOrganizations(){
        String key = "organizations";
        List<OrganizationsVO> list = (List<OrganizationsVO>) redisTemplate.opsForValue().get(key);

        if(list != null && list.size() > 0){
            return Result.success(list);
        }

        list = organizationService.getOrganizations();
        redisTemplate.opsForValue().set(key,list);
        return Result.success(list);
    }

    /**
     * 根据提供信息返回组织信息
     * @return
     */
    @PostMapping("/getOrganizationsByTag")
    @ApiOperation("根据信息查询")
    public Result<List<OrganizationsVO>> getOrganizationsByTag(@RequestBody GetInfoByTagDTO getInfoByTagDTO){
        log.info("请求组织信息为："+getInfoByTagDTO);

        String key = "organizations_" + getInfoByTagDTO.getTag();
        List<OrganizationsVO> list = (List<OrganizationsVO>) redisTemplate.opsForValue().get(key);

        if(list != null && list.size() > 0){
            return Result.success(list);
        }

        list = organizationService.getOrganizationsByTag(getInfoByTagDTO);
        return Result.success(list);
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
