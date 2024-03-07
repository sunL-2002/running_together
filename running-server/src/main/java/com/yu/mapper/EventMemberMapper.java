package com.yu.mapper;

import com.yu.dto.AddObjectDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EventMemberMapper {


    void insertEventMember(AddObjectDTO addObjectDTO);

    @Select("select user_openid from event_member where event_id=#{id}")
    List<String> getOpenidByEventId(int id);
    /**
     * 根据所属活动id删除成员
     * @param id
     */
    @Delete("delete from event_member where event_id=#{id}")
    void deleteByGroupId(int id);
}
