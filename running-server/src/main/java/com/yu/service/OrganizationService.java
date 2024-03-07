package com.yu.service;

import com.yu.dto.GetInfoByTagDTO;
import com.yu.dto.GroupPageQueryDTO;
import com.yu.dto.InsertGroupMemberDTO;
import com.yu.dto.RunningGroupDTO;
import com.yu.entity.User;
import com.yu.result.PageResult;
import com.yu.vo.OrganizationInfoVO;
import com.yu.vo.OrganizationsVO;
import com.yu.vo.PageMemberVO;

import java.util.List;

public interface OrganizationService {

    /**
     * 加入组织
     * @param insertGroupMemberDTO
     */
    int addGroupMember(InsertGroupMemberDTO insertGroupMemberDTO);

    /**
     * 查组员信息
     * @param id
     * @return
     */
    List<User> getMemberByGroupId(int id);

    /**
     * 根据id查信息
     * @return
     */
    OrganizationInfoVO getOrganizationInfo(int id);

    /**
     * 返回两条组织信息
     * @return
     */
    List<OrganizationsVO> getOrganization2();

    /**
     * 创建跑步组织
     * @param runningGroupDTO
     */
    void saveOrganization(RunningGroupDTO runningGroupDTO);

    /**
     * 查询组织信息
     * @return
     */
    List<OrganizationsVO> getOrganizations();

    /**
     * 根据提供信息返回组织信息
     * @return
     */
    List<OrganizationsVO> getOrganizationsByTag(GetInfoByTagDTO getInfoByTagDTO);

    /**
     * 跑团分页查询
     * @param groupPageQueryDTO
     * @return
     */
    PageResult pageQuery(GroupPageQueryDTO groupPageQueryDTO);

    /**
     * 跑团批量删除
     * @param ids
     */
    void deleteBach(List<Integer> ids);

    /**
     * 跑团成员
     * @param id
     * @return
     */
    List<PageMemberVO> pageMember(int id);
}
