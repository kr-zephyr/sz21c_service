package com.sz21c.statistics.service;

import com.sz21c.statistics.dao.DailyStatisticsDao;
import com.sz21c.statistics.model.DailyTopTenHitPostDto;
import com.sz21c.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyScheduleService {

    @Autowired
    DailyStatisticsDao dailyStatisticsDao;

    public int getDailyHitTotalCount() throws Exception {
        return dailyStatisticsDao.selectDailyHitTotalCount(
            DateTimeUtil.getDateForMidnightToday()
            , DateTimeUtil.getDateForMidnightYesterday());
    }

    public int getDailyVisitorCount() throws Exception {
        return dailyStatisticsDao.selectDailyVisitorCount(
            DateTimeUtil.getDateForMidnightToday()
            , DateTimeUtil.getDateForMidnightYesterday());
    }

    public List<DailyTopTenHitPostDto> getTopTenHitPost() throws Exception {
        return dailyStatisticsDao.selectDailyTopTenHitPost(
            DateTimeUtil.getDateForMidnightToday()
            , DateTimeUtil.getDateForMidnightYesterday());
    }
}
