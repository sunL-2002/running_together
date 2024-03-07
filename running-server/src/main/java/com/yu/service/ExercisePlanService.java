package com.yu.service;

import com.yu.dto.ExercisePlanInsertDTO;
import com.yu.dto.GetInfoByTagDTO;
import com.yu.vo.ExercisePlanVO;

import java.util.List;

public interface ExercisePlanService {

    /**
     * 根据日期查询计划
     * @param getInfoByTagDTO
     * @return
     */
    List<ExercisePlanVO> getPlanByTag(GetInfoByTagDTO getInfoByTagDTO);

    /**
     * 添加训练计划
     */
    void savePlan(ExercisePlanInsertDTO exercisePlanInsertDTO);

    /**
     * 根据ID删除计划
     * @param planId
     */
    void deleteById(int planId);

    /**
     * 修改计划
     * @param exercisePlanInsertDTO
     */
    void updatePlan(ExercisePlanInsertDTO exercisePlanInsertDTO);
}
