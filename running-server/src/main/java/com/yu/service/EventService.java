package com.yu.service;

import com.yu.dto.*;
import com.yu.entity.User;
import com.yu.result.PageResult;
import com.yu.vo.EventsVO;

import java.util.List;

public interface EventService {


    /**
     * 加入活动
     * @param addObjectDTO
     */
    int addEvent(AddObjectDTO addObjectDTO);
    /**
     * 查询活动成员
     * @param id
     * @return
     */
    List<User> getMemberByEventId(int id);
    /**
     * 根据id查询活动信息
     * @param id
     * @return
     */
    EventsVO getEventInfo(int id);


    /**
     * 活动分页查询
     * @param eventPageQueryDTO
     * @return
     */
    PageResult pageQuery(EventPageQueryDTO eventPageQueryDTO);

    /**
     * 活动批量删除
     * @param ids
     */
    void deleteBach(List<Integer> ids);

    /**
     * 返回两条活动数据
     * @return
     */
    List<EventsVO> getEvent2();

    /**
     * 创建活动
     * @param eventDTO
     */
    void saveEvent(EventDTO eventDTO);

    /**
     * 返回所有活动信息
     * @return
     */
    List<EventsVO> getEvents();

    /**
     * 根据提供信息返回活动信息
     * @return
     */
    List<EventsVO> getEventsByTag(GetInfoByTagDTO getInfoByTagDTO);
}
