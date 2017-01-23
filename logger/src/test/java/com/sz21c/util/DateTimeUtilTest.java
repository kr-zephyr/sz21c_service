package com.sz21c.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class DateTimeUtilTest {

    @Test
    public void test_getYestdayForKorean() {
        log.info("yesterday is " + DateTimeUtil.getYestdayForKorean());
    }
}
