package com.yu.controller.user;

import com.yu.dto.AddObjectDTO;
import com.yu.dto.EventDTO;
import com.yu.dto.GetInfoByTagDTO;
import com.yu.entity.User;
import com.yu.result.Result;
import com.yu.service.EventService;
import com.yu.vo.EventsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 活动管理
 */
@RestController("userEventController")
@RequestMapping("/user/events")
@Slf4j
@Api(tags = "活动相关接口")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/add")
    public Result addEvent(@RequestBody AddObjectDTO addObjectDTO){
        int i = eventService.addEvent(addObjectDTO);
        if(i == 0){
            return Result.error("您以加入该活动");
        }
        return Result.success();
    }

    /**
     * 活动成员，根据活动id查
     * @param id
     * @return
     */
    @GetMapping("/member/{id}")
    public Result<List<User>> getMemberByEventId(@PathVariable int id){
        List<User> memberByEventId = eventService.getMemberByEventId(id);
        return Result.success(memberByEventId);
    }
    /**
     * 请求活动信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<EventsVO> getEventInfo(@PathVariable int id){
        System.out.println("请求活动信息【"+id+"】");
        EventsVO eventInfo = eventService.getEventInfo(id);
        System.out.println(eventInfo);
        return Result.success(eventInfo);
    }


    @GetMapping("/getEvent2")
    @ApiOperation("返回活动信息")
    public Result<List<EventsVO>> getEvent2(){
        //构造redis中的key
        String key = "evnet";
        //查询rides中是否存在活动数据
        List<EventsVO> list = (List<EventsVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size()>0){
            //如果存在，直接返回，无须查询数据库
            return Result.success(list);
        }
        //如果不存在，查询数据库，将查询到的数据放入rides中
        list = eventService.getEvent2();
        redisTemplate.opsForValue().set(key,list);

        return Result.success(list);
    }

    /**
     * 创建活动
     * @param eventDTO
     * @return
     */
    @PostMapping("/saveEvent")
    @ApiOperation("创建活动")
    public Result saveEvent(@RequestBody EventDTO eventDTO){
        log.info("创建活动：{}",eventDTO);
        eventService.saveEvent(eventDTO);

        //清除缓存数据
        clearCache("evnets*");

        return Result.success();
    }

    /**
     * 返回更多活动信息
     * @return
     */
    @GetMapping("/getEvents")
    @ApiOperation("返回全部活动信息")
    public Result<List<EventsVO>> getEvents(){
        //构造redis中的key
        String key = "evnets";
        //查询rides中是否存在活动数据
        List<EventsVO> list = (List<EventsVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size()>0){
            //如果存在，直接返回，无须查询数据库
            return Result.success(list);
        }
        //如果不存在，查询数据库，将查询到的数据放入rides中
        list = eventService.getEvents();
        redisTemplate.opsForValue().set(key,list);

        return Result.success(list);
    }

    /**
     * 根据提供信息返回活动信息
     * @return
     */
    @PostMapping("/getEventsByTag")
    @ApiOperation("根据信息查询")
    public Result<List<EventsVO>> getEventsByTag(@RequestBody GetInfoByTagDTO getInfoByTagDTO){
        log.info("收到了一个活动请求"+ getInfoByTagDTO);

        String key = "events_"+getInfoByTagDTO.getTag();
        List<EventsVO> list = (List<EventsVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size() > 0){
            return Result.success(list);
        }

        list = eventService.getEventsByTag(getInfoByTagDTO);
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
