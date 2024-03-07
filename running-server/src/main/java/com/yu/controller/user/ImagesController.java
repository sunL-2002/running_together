package com.yu.controller.user;

import com.yu.result.Result;
import com.yu.service.ImagesService;
import com.yu.vo.ImagesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 图片管理
 */
@RestController
@RequestMapping("/user/images")
@Slf4j
@Api(tags = "图片相关接口")
public class ImagesController {

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 返回图片
     * @return
     */
    @GetMapping("/images")
    @ApiOperation("返回轮播图图片")
    public Result<List<ImagesVO>> images(){
        //构造rides中的key. 规则：dish_分类id
        String key = "images_1";

        //查询rides中是否存在图片数据
        List<ImagesVO> list = (List<ImagesVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size()>0){
            //如果存在，直接返回，无须查询数据库
            return Result.success(list);
        }

        //如果不存在，查询数据库，将查询到的数据放入rides中
        list = imagesService.images();
        redisTemplate.opsForValue().set(key,list);

        return Result.success(list);
    }
}
