package com.yu.mapper;

import com.github.pagehelper.Page;
import com.yu.dto.ChallengePageQueryDTO;
import com.yu.entity.ChallengePk;
import com.yu.vo.ChallengeInfoVO;
import com.yu.vo.ChallengePKPageQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChallengesMapper {

    ChallengeInfoVO getInfoById(int id);

    void updateChallenge(ChallengePk challengePk);

    /**
     * 分页查询
     * @param challengePageQueryDTO
     * @return
     */
    Page<ChallengePKPageQueryVO> pageQuery(ChallengePageQueryDTO challengePageQueryDTO);

    @Select("select user_id from user where username = #{name}")
    Integer selectByName(String name);
    /**
     * 返回数据行数
     * @return
     */
    @Select("select count(*) from challenge_pk")
    int getCount();

    /**
     * 返回两条数据
     * @return
     */
    @Select("select * from challenge_pk limit 2")
    List<ChallengePk> getList2();

    /**
     * 查询还未完成的挑战
     * @return
     */
    @Select("select * from challenge_pk where completion_status = 0")
    List<ChallengePk> getLists();

    /**
     * 根据标签查询
     * @param tag
     * @return
     */
    @Select("select * from challenge_pk where tag = #{tag} and completion_status = 0")
    List<ChallengePk> getChallengeByTag(String tag);
}
