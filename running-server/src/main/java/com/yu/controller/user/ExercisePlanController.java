package com.yu.controller.user;

import com.yu.dto.ExercisePlanInsertDTO;
import com.yu.dto.GetInfoByTagDTO;
import com.yu.result.Result;
import com.yu.service.ExercisePlanService;
import com.yu.vo.ExercisePlanVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 运动计划
 */
@RestController
@RequestMapping("/user/plans")
@Slf4j
@Api(tags = "运动计划相关接口")
public class ExercisePlanController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ExercisePlanService exercisePlanService;

    /**
     * 添加计划
     * @param exercisePlanInsertDTO
     * @return
     */
    @PostMapping("/insertPlan")
    @ApiOperation("添加计划")
    public Result savePlan(@RequestBody ExercisePlanInsertDTO exercisePlanInsertDTO){
        log.info("======="+exercisePlanInsertDTO);
        //将缓存数据清理掉，所有的以plan开头的key
        clearCache("plan*");

        exercisePlanService.savePlan(exercisePlanInsertDTO);
        return Result.success();
    }

    /**
     * 修改计划
     * @param exercisePlanInsertDTO
     * @return
     */
    @PostMapping("/updatePlan")
    @ApiOperation("修改计划")
    public Result updatePlan(@RequestBody ExercisePlanInsertDTO exercisePlanInsertDTO){
        log.info("======="+exercisePlanInsertDTO);
        //将缓存数据清理掉，所有的以plan开头的key
        clearCache("plan*");

        exercisePlanService.updatePlan(exercisePlanInsertDTO);
        return Result.success();
    }
    /**
     * 根据tag查询计划
     * @param getInfoByTagDTO
     * @return
     */
    @PostMapping("/getPlanByTag")
    @ApiOperation("根据给定信息查询")
    public Result<List<ExercisePlanVO>> getPlanByTag(@RequestBody GetInfoByTagDTO getInfoByTagDTO){
        String key = "plan_" + getInfoByTagDTO.getTag();
        List<ExercisePlanVO> list = (List<ExercisePlanVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size() > 0){
            return Result.success(list);
        }

        list = exercisePlanService.getPlanByTag(getInfoByTagDTO);
        redisTemplate.opsForValue().set(key,list);

        return Result.success(list);
    }

    /**
     * 删除计划
     * @param planID
     * @return
     */
    @PostMapping("/deletePlanById")
    @ApiOperation("删除计划")
    public Result deletePlanById(@RequestBody int planID){
        exercisePlanService.deleteById(planID);

        //将缓存数据清理掉，所有的以plan开头的key
        clearCache("plan*");
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
