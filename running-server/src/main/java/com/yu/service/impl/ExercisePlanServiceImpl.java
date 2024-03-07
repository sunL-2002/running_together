package com.yu.service.impl;

import com.yu.constant.MessageConstant;
import com.yu.dto.ExercisePlanInsertDTO;
import com.yu.dto.GetInfoByTagDTO;
import com.yu.entity.ExercisePlan;
import com.yu.exception.BaseException;
import com.yu.mapper.ExercisePlanMapper;
import com.yu.mapper.UserMapper;
import com.yu.service.ExercisePlanService;
import com.yu.vo.ExercisePlanVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExercisePlanServiceImpl implements ExercisePlanService {

    @Autowired
    private ExercisePlanMapper exercisePlanMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据日期查询计划
     * @param getInfoByTagDTO
     * @return
     */
    public List<ExercisePlanVO> getPlanByTag(GetInfoByTagDTO getInfoByTagDTO){
        LocalDate localDate = toLocalDate(formatDate(getInfoByTagDTO.getTag()));
        int userId = userMapper.getUserIdByOpenid(getInfoByTagDTO.getOpenid());
        List<ExercisePlan> list = exercisePlanMapper.getPlanByTag(localDate,userId);

        List<ExercisePlanVO> exercisePlanVOList = copyProperties(list);
        return exercisePlanVOList;
    }

    /**
     * 添加训练计划
     */
    public void savePlan(ExercisePlanInsertDTO exercisePlanInsertDTO){
        int userId = userMapper.getUserIdByOpenid(exercisePlanInsertDTO.getInitiatorId());

        ExercisePlan exercisePlan = ExercisePlan.builder()
                .userId(userId)
                .startDate(toLocalDate(exercisePlanInsertDTO.getStartDate()))
                .endDate(toLocalDate(exercisePlanInsertDTO.getEndDate()))
                .planGoal(exercisePlanInsertDTO.getPlanGoal())
                .build();

        exercisePlanMapper.insert(exercisePlan);
    }
    /**
     * 修改训练计划
     */
    public void updatePlan(ExercisePlanInsertDTO exercisePlanInsertDTO){

        ExercisePlan exercisePlan = ExercisePlan.builder()
                .planId(exercisePlanInsertDTO.getPlanId())
                .startDate(toLocalDate(exercisePlanInsertDTO.getStartDate()))
                .endDate(toLocalDate(exercisePlanInsertDTO.getEndDate()))
                .planGoal(exercisePlanInsertDTO.getPlanGoal())
                .build();

        exercisePlanMapper.update(exercisePlan);
    }

    /**
     * 根据ID删除计划
     * @param planId
     */
    public void deleteById(int planId){
        exercisePlanMapper.deleteById(planId);
    }

    /**
     * 将日期转换成标准模式
     */
    private String formatDate(String inputDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(inputDate);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new BaseException(MessageConstant.UNKNOWN_ERROR);
    }
    /**
     * 将String型的时间数据转换为localDate型
     * @param string
     * @return
     */
    private LocalDate toLocalDate(String string){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.parse(string, formatter);
        return localDate;
    }

    /**
     * 将获取到的ExercisePlan转化为ExercisePlanVO对象
     * @param exercisePlanList
     * @return
     */
    private List<ExercisePlanVO> copyProperties(List<ExercisePlan> exercisePlanList){
        List<ExercisePlanVO> exercisePlanVOList = new ArrayList<>();
        for(ExercisePlan e : exercisePlanList){
            ExercisePlanVO exercisePlanVO = new ExercisePlanVO();
            BeanUtils.copyProperties(e,exercisePlanVO);

            exercisePlanVOList.add(exercisePlanVO);
        }
        return exercisePlanVOList;
    }
}
