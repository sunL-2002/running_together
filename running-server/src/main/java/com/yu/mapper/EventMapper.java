package com.yu.mapper;

import com.github.pagehelper.Page;
import com.yu.annotation.AutoFill;
import com.yu.dto.EventPageQueryDTO;
import com.yu.dto.GroupPageQueryDTO;
import com.yu.entity.Event;
import com.yu.entity.RunningGroup;
import com.yu.entity.User;
import com.yu.enumeration.OperationType;
import com.yu.vo.EventsVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EventMapper {

    /**
     * 活动成员
     * @param id
     * @return
     */
    List<User> getMemberByEventId(int id);
    /**
     * 根据id返回信息
     * @param id
     * @return
     */
    EventsVO getEventInfo(int id);
    /**
     * 分页查询
     * @param eventPageQueryDTO
     * @return
     */
    Page<Event> pageQuery(EventPageQueryDTO eventPageQueryDTO);

    /**
     * 根据id删除活动
     * @param id
     */
    @Delete("delete from event where event_id = #{id}")
    void deleteById(int id);
    /**
     * 返回两条数据
     * @return
     */
    @Select("select * from event limit 2")
    List<Event> getList2();

    /**
     * 插入活动信息
     * @param event
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Event event);

    /**
     * 返回更多活动信息
     * @return
     */
    @Select("select * from event")
    List<Event> getLists();

    /**
     * 根据openid 查询活动信息
     * @param tag
     * @return
     */
    @Select("select * from event where initiator_id = #{tag}")
    List<Event> getListsByTag(String tag);

    /**
     * 根据openid查询所参加活动id
     * @param openid
     * @return
     */
    @Select("select event_id from event_member where user_openid = #{openid}")
    List<Integer> getEventId(String openid);

    /**
     * 根据主键查询活动信息
     * @param id
     * @return
     */
    @Select("select * from event where event_id = #{id}")
    Event getListById(int id);
}
