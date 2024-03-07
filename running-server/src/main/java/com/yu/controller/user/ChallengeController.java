package com.yu.controller.user;

import com.yu.dto.UpdateChallengeDTO;
import com.yu.result.Result;
import com.yu.service.ChallengesService;
import com.yu.vo.ChallengeInfoVO;
import com.yu.vo.ChallengesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 挑战管理
 */
@RestController
@RequestMapping("/user/challenges")
@Slf4j
@Api(tags = "挑战相关接口")
public class ChallengeController {

    @Autowired
    private ChallengesService challengesService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 接受挑战
     * @param updateChallengeDTO
     * @return
     */
    @PostMapping("/update")
    public Result updateChallenge(@RequestBody UpdateChallengeDTO updateChallengeDTO){
        System.out.println("接受挑战【"+updateChallengeDTO.getChallengeId()+"】");
        System.out.println(updateChallengeDTO+"====");
        challengesService.updateChallenge(updateChallengeDTO);
        return Result.success();
    }

    /**
     * 展示信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<ChallengeInfoVO> getChallengeInfo(@PathVariable int id){
        ChallengeInfoVO infoById = challengesService.getInfoById(id);
        return Result.success(infoById);
    }

    @GetMapping("/challenge2")
    @ApiOperation("返回2条挑战信息")
    public Result<List<ChallengesVO>> getChallenge2(){
        //构造rides中的key.
        String key = "Challenge";
        //查询rides中是否存在挑战数据
        List<ChallengesVO> list = (List<ChallengesVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size()>0){
            //如果存在，直接返回，无须查询数据库
            return Result.success(list);
        }

        //如果不存在，查询数据库，将查询到的数据放入rides中
        list = challengesService.getChallenge2();
        redisTemplate.opsForValue().set(key,list);

        return Result.success(list);
    }

    /**
     * 挑战大厅-查询所有未完成的挑战
     * @return
     */
    @GetMapping("/getChallenges")
    @ApiOperation("返回所有未完成挑战信息")
    public Result<List<ChallengesVO>> getChallenges(){
        System.out.println("user发起一条挑战请求");
        String key = "challenges";
        List<ChallengesVO> list = (List<ChallengesVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size() > 0){
            return Result.success(list);
        }

        list = challengesService.getChallenges();
        redisTemplate.opsForValue().set(key,list);

        return Result.success(list);
    }

    /**
     * 挑战大厅-根据标签查询
     * @return
     */
    @PostMapping("/getChallengeByTag")
    @ApiOperation("根据标签返回挑战信息")
    public Result<List<ChallengesVO>> getChallengeByTag(@RequestBody String tag){
        String key = tag;
        List<ChallengesVO> list = (List<ChallengesVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size() > 0){
            return Result.success(list);
        }

        list = challengesService.getChallengeByTag(tag);
        redisTemplate.opsForValue().set(key,list);

        return Result.success(list);
    }
}
