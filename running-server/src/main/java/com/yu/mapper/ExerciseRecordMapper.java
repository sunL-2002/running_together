package com.yu.mapper;

import com.yu.annotation.AutoFill;
import com.yu.entity.ExerciseRecord;
import com.yu.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExerciseRecordMapper {

    /**
     * 插入运动记录
     * @param exerciseRecord
     */
    @AutoFill(value = OperationType.INSERT)
    void insertRecord(ExerciseRecord exerciseRecord);

    /**
     * 查询
     * @param openid
     * @return
     */
    @Select("select * from exercise_record where user_openid = #{openid}")
    List<ExerciseRecord> selectByOpenid(String openid);
}
