package com.yu.service.impl;

import com.yu.service.RunTogetherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class RunTogetherServiceImplTest {

    @Resource
    private RunTogetherService runTogetherService;

    @Test
    void insertRunTogether() {
        runTogetherService.insertRunTogether("o7vOJ69EUwuGbmuG76I2EltqNGMo","昆明","2024-04-04","14:30:55");
    }
}
