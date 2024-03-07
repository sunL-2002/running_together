package com.yu.mapper;

import com.yu.annotation.AutoFill;
import com.yu.entity.ExercisePlan;
import com.yu.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ExercisePlanMapper {

    /**
     * 根据日期查询计划
     * @param localDate
     * @param id
     * @return
     */
    @Select("select * from exercise_plan where start_date <= #{localDate} and end_date > #{localDate} and user_id = #{id}")
    List<ExercisePlan> getPlanByTag(LocalDate localDate,int id);

    /**
     * 插入
     * @param exercisePlan
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(ExercisePlan exercisePlan);

    /**
     * 修改
     * @param exercisePlan
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(ExercisePlan exercisePlan);

    /**
     * 根据ID删除计划
     * @param planId
     */
    @Delete("delete from exercise_plan where plan_id = #{id}")
    void deleteById(int planId);
}
