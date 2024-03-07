package com.yu;

import com.yu.controller.user.ImagesController;
import com.yu.result.Result;
import com.yu.vo.ImagesVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RunningServerApplicationTests {

    @Autowired
    ImagesController imagesController;

    @Test
    void contextLoads() {
        Result<List<ImagesVO>> images = imagesController.images();
        System.out.println(images);
    }

}
