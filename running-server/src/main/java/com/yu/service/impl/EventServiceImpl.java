package com.yu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yu.constant.SelectConstant;
import com.yu.constant.MessageConstant;
import com.yu.dto.AddObjectDTO;
import com.yu.dto.EventDTO;
import com.yu.dto.EventPageQueryDTO;
import com.yu.dto.GetInfoByTagDTO;
import com.yu.entity.Event;
import com.yu.entity.RunningGroup;
import com.yu.entity.User;
import com.yu.exception.BaseException;
import com.yu.mapper.EventMapper;
import com.yu.mapper.EventMemberMapper;
import com.yu.result.PageResult;
import com.yu.service.EventService;
import com.yu.vo.EventsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventMemberMapper eventMemberMapper;

    public int addEvent(AddObjectDTO addObjectDTO){
        String openid = addObjectDTO.getOpenid();

        List<String> openidByEventId = eventMemberMapper.getOpenidByEventId(addObjectDTO.getId());
        for (String s: openidByEventId
             ) {
            if(s.equals(openid)){
                return 0;//用户已加入该活动
            }
        }

        eventMemberMapper.insertEventMember(addObjectDTO);
        return 1;
    }

    public List<User> getMemberByEventId(int id){
        List<User> memberByEventId = eventMapper.getMemberByEventId(id);
        return memberByEventId;
    }

    public EventsVO getEventInfo(int id){
        EventsVO eventInfo = eventMapper.getEventInfo(id);
        return eventInfo;
    }

    @Override
    public List<EventsVO> getEvent2() {
        List<Event> eventList = eventMapper.getList2();

        List<EventsVO> eventsVOList = copyProperties(eventList);
        return eventsVOList;
    }

    /**
     * 跑团批量删除
     * @param ids
     */
    @Transactional
    public void deleteBach(List<Integer> ids){
        for(int id : ids){
            //撤销活动
            eventMapper.deleteById(id);
            //删除活动成员
            eventMemberMapper.deleteByGroupId(id);
        }
    }

    /**
     * 分页查询
     * @param eventPageQueryDTO
     * @return
     */
    public PageResult pageQuery(EventPageQueryDTO eventPageQueryDTO){
        PageHelper.startPage(eventPageQueryDTO.getPage(),eventPageQueryDTO.getPageSize());
        Page<Event> events = eventMapper.pageQuery(eventPageQueryDTO);

        long total = events.getTotal();
        List<Event> groupsResult = events.getResult();

        return new PageResult(total,groupsResult);
    }

    /**
     * 创建活动
     * @param eventDTO
     */
    @Override
    public void saveEvent(EventDTO eventDTO) {
        LocalDateTime startDateTime = toLocalDatetime(eventDTO.getStartDatetime());
        LocalDateTime endDateTime = toLocalDatetime(eventDTO.getEndDatetime());

        Event event = new Event();
        BeanUtils.copyProperties(eventDTO,event);

        event.setStartDatetime(startDateTime);
        event.setEndDatetime(endDateTime);

        eventMapper.insert(event);
    }

    /**
     * 返回所有活动信息
     * @return
     */
    public List<EventsVO> getEvents(){
        List<Event> eventsList = eventMapper.getLists();

        List<EventsVO> eventsVOList = copyProperties(eventsList);
        return eventsVOList;
    }


    /**
     * 根据提供信息返回活动信息
     * @return
     */
    public List<EventsVO> getEventsByTag(GetInfoByTagDTO getInfoByTagDTO){
        String tag = getInfoByTagDTO.getTag();
        String openid = getInfoByTagDTO.getOpenid();
        List<Event> eventList = new ArrayList<>();
        //全部
        if(tag.equals(SelectConstant.SELECT_ALL)){
            List<Event> eventLists = eventMapper.getListsByTag(openid);
            for(Event e : eventLists){
                eventList.add(e);
            }
            List<Integer> eventId = eventMapper.getEventId(openid);
            for(int i : eventId){
                Event event = eventMapper.getListById(i);
                eventList.add(event);
            }
        //加入
        }else if(tag.equals(SelectConstant.SELECT_JOIN)){
            List<Integer> eventId = eventMapper.getEventId(openid);
            for(int i : eventId){
                Event events = eventMapper.getListById(i);
                eventList.add(events);
            }
        //创建
        }else if(tag.equals(SelectConstant.SELECT_FOUND)){
            eventList = eventMapper.getListsByTag(openid);
        //收藏
        }else if(tag.equals(SelectConstant.SELECT_COLLECT)){
            throw new BaseException(MessageConstant.NOT_ENABLED_SERVICE);
        //其他
        }else {
            throw new BaseException(MessageConstant.UNKNOWN_ERROR);
        }

        List<EventsVO> eventsVOList = copyProperties(eventList);
        return eventsVOList;
    }

    /**
     * 将String型的时间数据转换为localDatetime型
     * @param string
     * @return
     */
    private LocalDateTime toLocalDatetime(String string){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(string, formatter);
        return localDateTime;
    }


    /**
     * 将查询到的Event信息属性赋值到VO中
     * @param eventsList
     * @return
     */
    private List<EventsVO> copyProperties(List<Event> eventsList){
        List<EventsVO> eventsVOList = new ArrayList<>();

        for(Event e: eventsList){
            EventsVO eventsVO = new EventsVO();
            BeanUtils.copyProperties(e,eventsVO);

            eventsVOList.add(eventsVO);
        }

        return eventsVOList;
    }
}
