package com.sz21c.statistics.dao;

import com.sz21c.config.datasource.BaseDao;
import com.sz21c.statistics.model.DailyTopTenHitPostDto;
import com.sz21c.statistics.model.StatisticsSearchDto;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class DailyStatisticsDao extends BaseDao {

    public int selectDailyHitTotalCount(Date today, Date yesterday) throws Exception {
        StatisticsSearchDto statisticsSearchDto = new StatisticsSearchDto();
        statisticsSearchDto.setSDateTime(yesterday);
        statisticsSearchDto.setEDateTime(today);

        return getSqlSession().selectOne(getQueryStatement("selectDailyHitTotalCount"), statisticsSearchDto);
    }

    public int selectDailyVisitorCount(Date today, Date yesterday) throws Exception {
        StatisticsSearchDto statisticsSearchDto = new StatisticsSearchDto();
        statisticsSearchDto.setSDateTime(yesterday);
        statisticsSearchDto.setEDateTime(today);

        return getSqlSession().selectOne(getQueryStatement("selectDailyVisitorCount"), statisticsSearchDto);
    }

    public List<DailyTopTenHitPostDto> selectDailyTopTenHitPost(Date today, Date yesterday) throws Exception {
        StatisticsSearchDto statisticsSearchDto = new StatisticsSearchDto();
        statisticsSearchDto.setSDateTime(yesterday);
        statisticsSearchDto.setEDateTime(today);

        return getSqlSession().selectList(getQueryStatement("selectDailyTopTenHitPost"), statisticsSearchDto);
    }
}
