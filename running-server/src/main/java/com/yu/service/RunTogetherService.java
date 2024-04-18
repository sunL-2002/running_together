package com.yu.service;

import com.yu.dto.GetInfoByTagDTO;
import com.yu.dto.RunTogetherPageQueryDTO;
import com.yu.dto.UpdateRunTogetherDTO;
import com.yu.result.PageResult;
import com.yu.vo.RunTogetherInfoVO;
import com.yu.vo.RunTogetherVO;

import java.util.List;

public interface RunTogetherService {

    void insertRunTogether(String openid,String local,String date,String time);

    /**
     * 查询根据信息
     * @param getInfoByTagDTO
     * @return
     */
    List<RunTogetherVO> getByTag(GetInfoByTagDTO getInfoByTagDTO);

    /**
     * 分页
     * @param runTogetherPageQueryDTO
     * @return
     */
    PageResult pageQuery(RunTogetherPageQueryDTO runTogetherPageQueryDTO);

    RunTogetherInfoVO getRunTogetherInfo(int id);

    void updateRunTogether(UpdateRunTogetherDTO updateRunTogetherDTO);
}
