package com.yu.service;

import com.yu.dto.ChallengePageQueryDTO;
import com.yu.dto.UpdateChallengeDTO;
import com.yu.result.PageResult;
import com.yu.vo.ChallengeInfoVO;
import com.yu.vo.ChallengesVO;

import java.util.List;

public interface ChallengesService {

    /**
     * 接受挑战
     * @param updateChallengeDTO
     */
    void updateChallenge(UpdateChallengeDTO updateChallengeDTO);

    ChallengeInfoVO getInfoById(int id);
    /**
     * 返回2条挑战信息
     * @return
     */
    List<ChallengesVO> getChallenge2();

    PageResult pageQuery(ChallengePageQueryDTO challengePageQueryDTO);

    /**
     * 返回未完成挑战信息
     * @return
     */
    List<ChallengesVO> getChallenges();

    /**
     * 根据标签查询
     * @return
     */
    List<ChallengesVO> getChallengeByTag(String tag);
}
