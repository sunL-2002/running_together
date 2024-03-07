package com.yu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yu.dto.ChallengePageQueryDTO;
import com.yu.dto.UpdateChallengeDTO;
import com.yu.entity.ChallengePk;
import com.yu.mapper.ChallengesMapper;
import com.yu.mapper.UserMapper;
import com.yu.result.PageResult;
import com.yu.service.ChallengesService;
import com.yu.vo.ChallengeInfoVO;
import com.yu.vo.ChallengePKPageQueryVO;
import com.yu.vo.ChallengesVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengesServiceImpl implements ChallengesService {

    @Autowired
    private ChallengesMapper challengesMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 接受挑战
     * @param updateChallengeDTO
     */
    public void updateChallenge(UpdateChallengeDTO updateChallengeDTO){
        ChallengePk challengePk = new ChallengePk();
        challengePk.setChallengeId(updateChallengeDTO.getChallengeId());
        challengePk.setAcceptorId(userMapper.getUserIdByOpenid(updateChallengeDTO.getOpenid()));
        challengePk.setCompletionStatus((byte) 1);
        challengesMapper.updateChallenge(challengePk);
    }

    @Override
    public ChallengeInfoVO getInfoById(int id) {
        ChallengeInfoVO infoById = challengesMapper.getInfoById(id);
        return infoById;
    }


    public PageResult pageQuery(ChallengePageQueryDTO challengePageQueryDTO){
        PageHelper.startPage(challengePageQueryDTO.getPage(),challengePageQueryDTO.getPageSize());
        String initiator = challengePageQueryDTO.getInitiator();
        String acceptor = challengePageQueryDTO.getAcceptor();

//        challengePageQueryDTO.setInitiatorId(challengesMapper.selectByName(initiator));
//        challengePageQueryDTO.setAcceptorId(challengesMapper.selectByName(acceptor));


        Page<ChallengePKPageQueryVO> challengePkPage = challengesMapper.pageQuery(challengePageQueryDTO);

        long total = challengePkPage.getTotal();
        List<ChallengePKPageQueryVO> groupsResult = challengePkPage.getResult();

        return new PageResult(total,groupsResult);
    }



    /**
     * 返回两条挑战信息
     * @return
     */
    @Override
    public List<ChallengesVO> getChallenge2() {
        List<ChallengePk> challengePkList = challengesMapper.getList2();

        List<ChallengesVO> challengesVOList = copyProperties(challengePkList);
        return challengesVOList;
    }

    /**
     * 返回多条挑战信息
     * @return
     */
    @Override
    public List<ChallengesVO> getChallenges() {
        List<ChallengePk> challengePkList = challengesMapper.getLists();

        List<ChallengesVO> challengesVOList = copyProperties(challengePkList);
        return challengesVOList;
    }

    /**
     * 根据标签查询
     * @return
     */
    public List<ChallengesVO> getChallengeByTag(String tag){
        List<ChallengePk> challengePkList = challengesMapper.getChallengeByTag(tag);

        List<ChallengesVO> challengesVOList = copyProperties(challengePkList);
        return challengesVOList;
    }

    /**
     * 将查询到的ChallengePK信息属性赋值到VO中
     * @param challengePKList
     * @return
     */
    private List<ChallengesVO> copyProperties(List<ChallengePk> challengePKList){
        List<ChallengesVO> challengesVOList = new ArrayList<>();

        for(ChallengePk c : challengePKList){
            ChallengesVO challengesVO= new ChallengesVO();
            BeanUtils.copyProperties(c,challengesVO);

            challengesVOList.add(challengesVO);
        }
        return challengesVOList;
    }
}
