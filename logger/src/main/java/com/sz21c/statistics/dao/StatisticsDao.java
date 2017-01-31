package com.sz21c.statistics.dao;

import com.sz21c.config.datasource.BaseDao;
import com.sz21c.statistics.model.TopTenHitPostDto;
import com.sz21c.statistics.model.StatisticsSearchDto;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class StatisticsDao extends BaseDao {

    public int selectHitTotalCount(Date sDate, Date eDate) throws Exception {
        StatisticsSearchDto statisticsSearchDto = new StatisticsSearchDto();
        statisticsSearchDto.setSDateTime(sDate);
        statisticsSearchDto.setEDateTime(eDate);

        return getSqlSession().selectOne(getQueryStatement("selectHitTotalCount"), statisticsSearchDto);
    }

    public int selectVisitorCount(Date sDate, Date eDate) throws Exception {
        StatisticsSearchDto statisticsSearchDto = new StatisticsSearchDto();
        statisticsSearchDto.setSDateTime(sDate);
        statisticsSearchDto.setEDateTime(eDate);

        return getSqlSession().selectOne(getQueryStatement("selectVisitorCount"), statisticsSearchDto);
    }

    public List<TopTenHitPostDto> selectTopTenHitPost(Date sDate, Date eDate) throws Exception {
        StatisticsSearchDto statisticsSearchDto = new StatisticsSearchDto();
        statisticsSearchDto.setSDateTime(sDate);
        statisticsSearchDto.setEDateTime(eDate);

        return getSqlSession().selectList(getQueryStatement("selectTopTenHitPost"), statisticsSearchDto);
    }
}
