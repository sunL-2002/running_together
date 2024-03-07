package com.yu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yu.constant.MessageConstant;
import com.yu.constant.SelectConstant;
import com.yu.dto.GetInfoByTagDTO;
import com.yu.dto.RunTogetherPageQueryDTO;
import com.yu.dto.UpdateRunTogetherDTO;
import com.yu.entity.NearbyRunning;
import com.yu.exception.BaseException;
import com.yu.mapper.RunTogetherMapper;
import com.yu.mapper.UserMapper;
import com.yu.result.PageResult;
import com.yu.service.RunTogetherService;
import com.yu.vo.RunTogetherInfoVO;
import com.yu.vo.RunTogetherPageQueryVO;
import com.yu.vo.RunTogetherVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RunTogetherServiceImpl implements RunTogetherService{

    @Autowired
    private RunTogetherMapper runTogetherMapper;

    @Autowired
    private UserMapper userMapper;


    public void updateRunTogether(UpdateRunTogetherDTO updateRunTogetherDTO){
        NearbyRunning nearbyRunning = new NearbyRunning();
        nearbyRunning.setRunLocation(updateRunTogetherDTO.getRunLocation());
        nearbyRunning.setRunDatetime(toLocalDatetime(updateRunTogetherDTO.getRunDatetime()));
        nearbyRunning.setRunId(updateRunTogetherDTO.getId());
        nearbyRunning.setInitiatorId(runTogetherMapper.getIdByName(updateRunTogetherDTO.getInitiator()));
        nearbyRunning.setAcceptorId(runTogetherMapper.getIdByOpenid(updateRunTogetherDTO.getOpenid()));
        System.out.println("nearbyRunning=="+nearbyRunning);
        runTogetherMapper.updateRunTogether(nearbyRunning);
    }

    /**
     * 根据id查
     * @param id
     * @return
     */
    public RunTogetherInfoVO getRunTogetherInfo(int id){
        RunTogetherInfoVO info = runTogetherMapper.getRunTogetherInfo(id);
        System.out.println("info==="+info);
        return info;
    }

    /**
     * 分页
     * @param runTogetherPageQueryDTO
     * @return
     */
    public PageResult pageQuery(RunTogetherPageQueryDTO runTogetherPageQueryDTO){
        PageHelper.startPage(runTogetherPageQueryDTO.getPage(),runTogetherPageQueryDTO.getPageSize());

        Page<RunTogetherPageQueryVO> runTogetherPageQuery = runTogetherMapper.pageQuery(runTogetherPageQueryDTO);

        long total = runTogetherPageQuery.getTotal();
        List<RunTogetherPageQueryVO> result = runTogetherPageQuery.getResult();
        return new PageResult(total,result);
    }

    /**
     * 查询根据信息
     * @param getInfoByTagDTO
     * @return
     */
    public List<RunTogetherVO> getByTag(GetInfoByTagDTO getInfoByTagDTO){
        String tag = getInfoByTagDTO.getTag();
        System.out.println("tag====="+tag);
        String openid = getInfoByTagDTO.getOpenid();
        Integer userId = userMapper.getUserIdByOpenid(openid);
        System.out.println("userID====="+userId);

        //全部
        if(tag.equals(SelectConstant.SELECT_ALL)){
            List<NearbyRunning> nearbyRunningList = runTogetherMapper.getInfo(userId);

            List<RunTogetherVO> runTogetherVOList = copyProperties(nearbyRunningList);
            System.out.println("runTogetherVOList====="+ runTogetherVOList);
            return runTogetherVOList;
            //加入
        }else if(tag.equals(SelectConstant.SELECT_JOIN)){
            List<NearbyRunning> nearbyRunningList = runTogetherMapper.getInfoByAcceptorId(userId);

            List<RunTogetherVO> runTogetherVOList = copyProperties(nearbyRunningList);
            return runTogetherVOList;
            //创建
        }else if(tag.equals(SelectConstant.SELECT_FOUND)){
            List<NearbyRunning> nearbyRunningList = runTogetherMapper.getInfoByInitiatorId(userId);

            List<RunTogetherVO> runTogetherVOList = copyProperties(nearbyRunningList);
            return runTogetherVOList;
        } else {
            throw new BaseException(MessageConstant.UNKNOWN_ERROR);
        }
    }

    /**
     * 将查询到的nearbyRunning赋值为RunTogetherVO
     * @param nearbyRunning
     * @return
     */
    private List<RunTogetherVO> copyProperties(List<NearbyRunning> nearbyRunning){
        List<RunTogetherVO> runTogetherVOList = new ArrayList<>();

        for(NearbyRunning n : nearbyRunning){
            RunTogetherVO runTogetherVO = new RunTogetherVO();
            BeanUtils.copyProperties(n,runTogetherVO);

            runTogetherVOList.add(runTogetherVO);
        }
        return runTogetherVOList;
    }

    private LocalDateTime toLocalDatetime(String string){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(string, formatter);
        return localDateTime;
    }
}
