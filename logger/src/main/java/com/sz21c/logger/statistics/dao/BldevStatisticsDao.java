package com.sz21c.logger.statistics.dao;

import com.sz21c.logger.config.datasource.BaseDao;
import com.sz21c.logger.statistics.model.BldevVisitGooglebotCntDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BldevStatisticsDao extends BaseDao {

    public List<BldevVisitGooglebotCntDto> selectBldevVisitGooglebotCnt() throws Exception {
        return getSqlSession().selectList(getQueryStatement("selectBldevVisitGooglebotCnt"));
    }
}
