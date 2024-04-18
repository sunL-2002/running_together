package com.yu.controller.user;

import com.yu.dto.GetInfoByTagDTO;
import com.yu.dto.InsertRunTogetherDTO;
import com.yu.dto.UpdateRunTogetherDTO;
import com.yu.result.Result;
import com.yu.service.RunTogetherService;
import com.yu.vo.RunTogetherInfoVO;
import com.yu.vo.RunTogetherVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user/runTogether")
@Slf4j
@Api(tags = "约跑相关接口")
public class RunTogetherController {

    @Autowired
    private RunTogetherService runTogetherService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 发起约跑
     * @return
     */
    @PostMapping("/insert")
    public Result insertRunTogether(@RequestBody InsertRunTogetherDTO insertRunTogetherDTO){
        String openid = insertRunTogetherDTO.getOpenid();
        String local = insertRunTogetherDTO.getLocal();
        String date = insertRunTogetherDTO.getDate();
        String time = insertRunTogetherDTO.getTime();
        runTogetherService.insertRunTogether(openid,local,date,time);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation("接受邀请")
    public Result updateRunTogether(@RequestBody UpdateRunTogetherDTO updateRunTogetherDTO){
        System.out.println("修改约跑");
        runTogetherService.updateRunTogether(updateRunTogetherDTO);
        //清除缓存数据
        clearCache("runTogether*");
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<RunTogetherInfoVO> getRunTogetherInfo(@PathVariable int id){
        System.out.println("发请信息Info["+id+"]");
        RunTogetherInfoVO info = runTogetherService.getRunTogetherInfo(id);
        System.out.println(info);
        return Result.success(info);
    }


    /**
     * 根据提供信息返回约跑信息
     * @return
     */
    @PostMapping("/getRunTogetherByTag")
    @ApiOperation("根据信息查询")
    public Result<List<RunTogetherVO>> getRunTogetherByTag(@RequestBody GetInfoByTagDTO getInfoByTagDTO){
        log.info("约跑查询：+++" + getInfoByTagDTO);
        String key = "runTogether_" + getInfoByTagDTO.getTag();
        List<RunTogetherVO> list = (List<RunTogetherVO>) redisTemplate.opsForValue().get(key);

        if(list != null && list.size()>0){
            return Result.success(list);
        }
        list = runTogetherService.getByTag(getInfoByTagDTO);
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
