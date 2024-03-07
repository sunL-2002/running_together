package com.yu.service;

import com.yu.dto.ExerciseRecordInsertDTO;
import com.yu.vo.ExerciseRecordGetVO;

import java.util.List;

public interface ExerciseRecordService {

    /**
     * 插入运动记录
     * @param exerciseRecordInsertDTO
     */
    void insertRecord(ExerciseRecordInsertDTO exerciseRecordInsertDTO);

    /**
     * 查询
     * @param openid
     * @return
     */
    List<ExerciseRecordGetVO> getRecord(String openid);
}
