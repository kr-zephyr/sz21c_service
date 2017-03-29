package com.sz21c.logger.statistics.service;

import com.sz21c.logger.statistics.dao.StatisticsDao;
import com.sz21c.logger.statistics.model.TopTenHitPostDto;
import com.sz21c.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyScheduleService {

    @Autowired
    StatisticsDao dailyStatisticsDao;

    public int getDailyHitTotalCount() throws Exception {
        return dailyStatisticsDao.selectHitTotalCount(
            DateTimeUtil.getDateForMidnightYesterday()
            , DateTimeUtil.getDateForMidnightToday());
    }

    public int getDailyVisitorCount() throws Exception {
        return dailyStatisticsDao.selectVisitorCount(
            DateTimeUtil.getDateForMidnightYesterday()
            , DateTimeUtil.getDateForMidnightToday());
    }

    public List<TopTenHitPostDto> getDailyTopTenHitPost() throws Exception {
        return dailyStatisticsDao.selectTopTenHitPost(
            DateTimeUtil.getDateForMidnightYesterday()
            , DateTimeUtil.getDateForMidnightToday());
    }
}
