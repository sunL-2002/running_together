package com.yu.service;

import com.yu.vo.ImagesVO;

import java.util.List;

public interface ImagesService {

    /**
     * 返回图片
     * @return
     */
    List<ImagesVO> images();
}
