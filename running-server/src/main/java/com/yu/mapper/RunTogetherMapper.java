package com.yu.mapper;

import com.github.pagehelper.Page;
import com.yu.dto.RunTogetherPageQueryDTO;
import com.yu.dto.UpdateRunTogetherDTO;
import com.yu.entity.NearbyRunning;
import com.yu.vo.RunTogetherInfoVO;
import com.yu.vo.RunTogetherPageQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RunTogetherMapper {

    @Select("select * from nearby_running where acceptor_id = #{userId}")
    List<NearbyRunning> getInfoByAcceptorId(Integer userId);

    @Select("select * from nearby_running where initiator_id = #{userId}")
    List<NearbyRunning> getInfoByInitiatorId(Integer userId);

    @Select("select * from nearby_running where acceptor_id is null && initiator_id != #{userId}")
    List<NearbyRunning> getInfo(Integer userId);

    Page<RunTogetherPageQueryVO> pageQuery(RunTogetherPageQueryDTO runTogetherPageQueryDTO);

    RunTogetherInfoVO getRunTogetherInfo(int id);

    void updateRunTogether(NearbyRunning nearbyRunning);

    int getIdByName(String name);

    @Select("select user_id from user where openid = #{openid}")
    int getIdByOpenid(String openid);


}
