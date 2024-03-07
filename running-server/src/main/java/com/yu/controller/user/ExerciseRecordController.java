package com.yu.controller.user;

import com.yu.dto.ExerciseRecordInsertDTO;
import com.yu.result.Result;
import com.yu.service.ExerciseRecordService;
import com.yu.vo.ExerciseRecordGetVO;
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

@RestController
@RequestMapping("/user/exerciseRecord")
@Slf4j
@Api(tags = "运动记录相关接口")
public class ExerciseRecordController {

    @Autowired
    private ExerciseRecordService exerciseRecordService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加运动记录
     * @param exerciseRecordInsertDTO
     * @return
     */
    @PostMapping("/insertRecord")
    @ApiOperation("添加运动记录")
    public Result insertRecord(@RequestBody ExerciseRecordInsertDTO exerciseRecordInsertDTO){
        log.info("插入一条运动记录"+exerciseRecordInsertDTO);

        exerciseRecordService.insertRecord(exerciseRecordInsertDTO);

        clearCache("record*");
        return Result.success();
    }

    /**
     * 查询运动记录
     * @param openid
     * @return
     */
    @PostMapping("/getRecord")
    @ApiOperation("查询运动记录")
    public Result<List<ExerciseRecordGetVO>> getRecord(@RequestBody String openid){
        String key = "record_" + openid;

        List<ExerciseRecordGetVO> list = (List<ExerciseRecordGetVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size() > 0){
            return Result.success(list);
        }

        list = exerciseRecordService.getRecord(openid);
        redisTemplate.opsForValue().set(key,list);

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
