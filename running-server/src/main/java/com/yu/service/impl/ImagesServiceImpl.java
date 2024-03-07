package com.yu.service.impl;

import com.yu.service.ImagesService;
import com.yu.vo.ImagesVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImagesServiceImpl implements ImagesService {

    /**
     * 返回图片
     * @return
     */
    public List<ImagesVO> images(){
        List<ImagesVO> list = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            ImagesVO image = new ImagesVO();
            image.setId(i);
            image.setUrl("/image/lbt" +i+".jpg");
            list.add(i,image);
        }
        return list;
    }
}
