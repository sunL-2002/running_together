package com.yu.service.impl;

import com.yu.dto.ExerciseRecordInsertDTO;
import com.yu.dto.PlaylineDTO;
import com.yu.entity.ExerciseRecord;
import com.yu.mapper.ExerciseRecordMapper;
import com.yu.service.ExerciseRecordService;
import com.yu.vo.ExerciseRecordGetVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ExerciseRecordServiceImpl implements ExerciseRecordService {

    @Autowired
    private ExerciseRecordMapper exerciseRecordMapper;

    /**
     * 插入运动记录
     * @param exerciseRecordInsertDTO
     */
    public void insertRecord(ExerciseRecordInsertDTO exerciseRecordInsertDTO){

        List<PlaylineDTO> runningRoute = exerciseRecordInsertDTO.getRunningRoute();
        String result = runningRoute.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        ExerciseRecord exerciseRecord = ExerciseRecord.builder()
                .userOpenid(exerciseRecordInsertDTO.getUserOpenid())
                .exerciseDate(LocalDate.now())
                .runningRoute(result)
                .distance(exerciseRecordInsertDTO.getDistance())
                .time(exerciseRecordInsertDTO.getTime())
                .build();

        exerciseRecordMapper.insertRecord(exerciseRecord);
    }

    /**
     * 查询
     * @param openid
     * @return
     */
    public List<ExerciseRecordGetVO> getRecord(String openid){
        List<ExerciseRecord> exerciseRecords = exerciseRecordMapper.selectByOpenid(openid);

        List<ExerciseRecordGetVO> list = copyProperties(exerciseRecords);

        return list;
    }

    /**
     * 将查询到的ExerciseRecord转存为ExerciseRecordGetVO
     * @param exerciseRecords
     * @return
     */
    private List<ExerciseRecordGetVO> copyProperties(List<ExerciseRecord> exerciseRecords){
        List<ExerciseRecordGetVO> list = new ArrayList<>();

        for(ExerciseRecord e:exerciseRecords){
            ExerciseRecordGetVO exerciseRecordGetVO = new ExerciseRecordGetVO();
            BeanUtils.copyProperties(e,exerciseRecordGetVO);

            String str = e.getRunningRoute();
            List<PlaylineDTO> playlineDTOS = parseStringToPlaylineDTOList(str);
            exerciseRecordGetVO.setRunningRoute(playlineDTOS);

            list.add(exerciseRecordGetVO);
        }

        return list;
    }

    /**
     * 将String转化为List<PlaylineDTO>
     * @param input
     * @return
     */
    public static List<PlaylineDTO> parseStringToPlaylineDTOList(String input) {
        List<PlaylineDTO> playlineDTOList = new ArrayList<>();
        Pattern pattern = Pattern.compile("playlineDTO\\(latitude=(\\d+\\.\\d+), longitude=(\\d+\\.\\d+)\\)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            PlaylineDTO playlineDTO = new PlaylineDTO();
            playlineDTO.setLatitude(Double.parseDouble(matcher.group(1)));
            playlineDTO.setLongitude(Double.parseDouble(matcher.group(2)));
            playlineDTOList.add(playlineDTO);
        }
        return playlineDTOList;
    }
}
