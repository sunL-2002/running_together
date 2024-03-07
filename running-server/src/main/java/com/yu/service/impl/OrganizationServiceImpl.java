package com.yu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yu.constant.SelectConstant;
import com.yu.constant.MessageConstant;
import com.yu.dto.GetInfoByTagDTO;
import com.yu.dto.GroupPageQueryDTO;
import com.yu.dto.InsertGroupMemberDTO;
import com.yu.dto.RunningGroupDTO;
import com.yu.entity.RunningGroup;
import com.yu.entity.User;
import com.yu.exception.BaseException;
import com.yu.mapper.OrganizationMapper;
import com.yu.mapper.GroupMemberMapper;
import com.yu.result.PageResult;
import com.yu.service.OrganizationService;
import com.yu.vo.OrganizationInfoVO;
import com.yu.vo.OrganizationsVO;
import com.yu.vo.PageMemberVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private GroupMemberMapper groupMemberMapper;


    public int addGroupMember(InsertGroupMemberDTO insertGroupMemberDTO){
        String openid = insertGroupMemberDTO.getUserOpenid();
        List<String> id = groupMemberMapper.getOpenidByGroupId(insertGroupMemberDTO.getGroupId());
        for (String s: id)
        {
            if(s.equals(openid)){
                return 0;//已存在
            }
        }
        groupMemberMapper.insertMember(insertGroupMemberDTO);
        return 1;
    }

    public List<User> getMemberByGroupId(int id){
        List<User> memberByGroupId = organizationMapper.getMemberByGroupId(id);
        return memberByGroupId;
    }

    @Override
    public OrganizationInfoVO getOrganizationInfo(int id) {
        OrganizationInfoVO organizationInfo = organizationMapper.getOrganizationInfo(id);
        return organizationInfo;
    }

    /**
     * 跑团成员
     * @param id
     * @return
     */
    public List<PageMemberVO> pageMember(int id){
        List<PageMemberVO> pageMemberVOS = organizationMapper.pageMember(id);
        return  pageMemberVOS;
    }

    /**
     * 返回两条组织信息
     * @return
     */
    @Override
    public List<OrganizationsVO> getOrganization2() {
        List<RunningGroup> runningGroupList = organizationMapper.getList2();

        List<OrganizationsVO> organizationsVOList = copyProperties(runningGroupList);
        return organizationsVOList;
    }

    /**
     * 创建跑步组织
     * @param runningGroupDTO
     */
    public void saveOrganization(RunningGroupDTO runningGroupDTO){
        RunningGroup runningGroup = new RunningGroup();

        BeanUtils.copyProperties(runningGroupDTO,runningGroup);

        organizationMapper.insert(runningGroup);
    }

    /**
     * 查询组织信息
     * @return
     */
    public List<OrganizationsVO> getOrganizations(){
        List<RunningGroup> list = organizationMapper.getOrganizations();

        List<OrganizationsVO> OrganizationsVOList = copyProperties(list);

        return OrganizationsVOList;
    }

    /**
     * 根据提供信息返回组织信息
     * @return
     */
    public List<OrganizationsVO> getOrganizationsByTag(GetInfoByTagDTO getInfoByTagDTO){
        String tag = getInfoByTagDTO.getTag();
        String openid = getInfoByTagDTO.getOpenid();
        List<RunningGroup> runningGroupLists = new ArrayList<>();
        //全部
        if(tag.equals(SelectConstant.SELECT_ALL)){
            List<RunningGroup> runningGroupList = organizationMapper.getListsByTag(openid);
            for(RunningGroup e : runningGroupList){
                runningGroupLists.add(e);
            }
            List<Integer> groupId = organizationMapper.getGroupId(openid);
            for(int i : groupId){
                RunningGroup runningGroup = organizationMapper.getListById(i);
                runningGroupLists.add(runningGroup);
            }
            //加入
        }else if(tag.equals(SelectConstant.SELECT_JOIN)){
            List<Integer> groupId = organizationMapper.getGroupId(openid);
            for(int i : groupId){
                RunningGroup runningGroup = organizationMapper.getListById(i);
                runningGroupLists.add(runningGroup);
            }
            //创建
        }else if(tag.equals(SelectConstant.SELECT_FOUND)){
            runningGroupLists = organizationMapper.getListsByTag(openid);
            //收藏
        }else if(tag.equals(SelectConstant.SELECT_COLLECT)){
            throw new BaseException(MessageConstant.NOT_ENABLED_SERVICE);
            //其他
        }else {
            throw new BaseException(MessageConstant.UNKNOWN_ERROR);
        }

        List<OrganizationsVO> organizationsVOList = copyProperties(runningGroupLists);
        return organizationsVOList;
    }

    /**
     * 跑团分页查询
     * @param groupPageQueryDTO
     * @return
     */
    public PageResult pageQuery(GroupPageQueryDTO groupPageQueryDTO){
        PageHelper.startPage(groupPageQueryDTO.getPage(),groupPageQueryDTO.getPageSize());
        Page<RunningGroup> runningGroups = organizationMapper.pageQuery(groupPageQueryDTO);

        long total = runningGroups.getTotal();
        List<RunningGroup> groupsResult = runningGroups.getResult();

        return new PageResult(total,groupsResult);
    }



    /**
     * 跑团批量删除
     * @param ids
     */
    @Transactional
    public void deleteBach(List<Integer> ids){
        for(int id : ids){
            organizationMapper.deleteById(id);

            groupMemberMapper.deleteByGroupId(id);
        }
    }

    /**
     * 将查询到的Organization信息属性赋值到VO中
     * @param runningGroupList
     * @return
     */
    private List<OrganizationsVO> copyProperties(List<RunningGroup> runningGroupList){
        List<OrganizationsVO> organizationsVOList = new ArrayList<>();

        for(RunningGroup e: runningGroupList){
            OrganizationsVO organizationsVO = new OrganizationsVO();
            BeanUtils.copyProperties(e,organizationsVO);

            organizationsVOList.add(organizationsVO);
        }

        return organizationsVOList;
    }
}
