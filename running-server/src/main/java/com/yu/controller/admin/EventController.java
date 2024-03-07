package com.yu.controller.admin;

import com.yu.dto.EventPageQueryDTO;
import com.yu.result.PageResult;
import com.yu.result.Result;
import com.yu.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/Event")
@Slf4j
@Api(tags = "活动相关接口")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/page")
    @ApiOperation("活动分页查询")
    public Result<PageResult> page(EventPageQueryDTO eventPageQueryDTO){
        System.out.println("收到一条活动请求==========="+eventPageQueryDTO);
        PageResult pageResult = eventService.pageQuery(eventPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("活动批量删除")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除了："+ids);
        eventService.deleteBach(ids);

        //将所有的菜品缓存数据清理掉，所有的以dish_开头的key
        clearCache("event*");

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
