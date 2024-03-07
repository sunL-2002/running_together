package com.yu.mapper;

import com.github.pagehelper.Page;
import com.yu.annotation.AutoFill;
import com.yu.dto.GroupPageQueryDTO;
import com.yu.dto.RunningGroupDTO;
import com.yu.entity.RunningGroup;
import com.yu.entity.User;
import com.yu.enumeration.OperationType;
import com.yu.vo.OrganizationInfoVO;
import com.yu.vo.PageMemberVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrganizationMapper {



    /**
     * 获取组织成员
     * @param id
     * @return
     */
    List<User> getMemberByGroupId(int id);

    /**
     * 组织信息
     * @param id
     * @return
     */
    OrganizationInfoVO getOrganizationInfo(int id);
    /**
     * 返回两条组织数据
     * @return
     */
    @Select("select * from running_group limit 2")
    List<RunningGroup> getList2();

    /**
     * 插入跑团组织
     * @param runningGroup
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(RunningGroup runningGroup);

    /**
     * 查询组织
     * @return
     */
    @Select("select * from running_group")
    List<RunningGroup> getOrganizations();

    /**
     * 根据openid 查询组织信息
     * @param openid
     * @return
     */
    @Select("select * from running_group where initiator_id = #{openid}")
    List<RunningGroup> getListsByTag(String openid);

    /**
     * 根据openid查询所参加组织id
     * @param openid
     * @return
     */
    @Select("select group_id from group_member where user_openid = #{openid}")
    List<Integer> getGroupId(String openid);

    /**
     * 根据主键查询组织信息
     * @param id
     * @return
     */
    @Select("select * from running_group where group_id = #{id}")
    RunningGroup getListById(int id);

    /**
     * 分页查询
     * @param groupPageQueryDTO
     * @return
     */
    Page<RunningGroup> pageQuery(GroupPageQueryDTO groupPageQueryDTO);

    /**
     * 根据id删除跑团
     * @param id
     */
    @Delete("delete from running_group where group_id = #{id}")
    void deleteById(int id);

    /**
     * 跑团成员
     * @param id
     * @return
     */
    List<PageMemberVO> pageMember(int id);
}
