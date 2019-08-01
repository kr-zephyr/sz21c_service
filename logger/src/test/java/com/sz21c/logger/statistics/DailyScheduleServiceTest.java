package com.sz21c.logger.statistics;

import com.sz21c.logger.LoggerApplication;
import com.sz21c.logger.statistics.service.DailyScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {LoggerApplication.class})
public class DailyScheduleServiceTest {

    @Autowired
    private DailyScheduleService dailyScheduleService;

//    @Test
    public void test_getDailyHitTotalCount() throws Exception {
        int totCnt = dailyScheduleService.getDailyHitTotalCount();
        log.debug("totCnt :: " + totCnt);
    }
}
