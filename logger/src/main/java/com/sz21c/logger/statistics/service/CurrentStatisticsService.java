package com.sz21c.logger.statistics.service;

import com.sz21c.logger.statistics.dao.BldevStatisticsDao;
import com.sz21c.logger.statistics.dao.StatisticsDao;
import com.sz21c.logger.statistics.model.BldevVisitGooglebotCntDto;
import com.sz21c.logger.statistics.model.TopTenHitPostDto;
import com.sz21c.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentStatisticsService {

    @Autowired
    private BldevStatisticsDao bldevStatisticsDao;

    @Autowired
    private StatisticsDao dailyStatisticsDao;

    public List<BldevVisitGooglebotCntDto> getBldevVisitGoogleBotCnt() throws Exception {
        return bldevStatisticsDao.selectBldevVisitGooglebotCnt();
    }

    public int getCurrentHitTotalCount() throws Exception {
        return dailyStatisticsDao.selectHitTotalCount(
            DateTimeUtil.getDateForMidnightToday()
            , DateTimeUtil.getCurrent());
    }

    public int getCurrentVisitorCount() throws Exception {
        return dailyStatisticsDao.selectVisitorCount(
            DateTimeUtil.getDateForMidnightToday()
            , DateTimeUtil.getCurrent());
    }

    public List<TopTenHitPostDto> getCurrentTopTenHitPost() throws Exception {
        return dailyStatisticsDao.selectTopTenHitPost(
            DateTimeUtil.getDateForMidnightToday()
            , DateTimeUtil.getCurrent());
    }
}
