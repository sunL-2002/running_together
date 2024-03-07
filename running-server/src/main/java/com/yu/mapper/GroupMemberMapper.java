package com.yu.mapper;

import com.yu.dto.InsertGroupMemberDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMemberMapper {

    void insertMember(InsertGroupMemberDTO insertGroupMemberDTO);
    /**
     * 根据所属跑团删除成员
     * @param id
     */
    @Delete("delete from group_member where group_id = #{id}")
    void deleteByGroupId(int id);

    /**
     * 查询组织中已加入的用户
     * @param groupId
     * @return
     */
    @Select("select user_openid from group_member where group_id = #{groupId}")
    List<String> getOpenidByGroupId(int groupId);
}
