package com.sz21c.collector.dao;

import com.sz21c.collector.model.MstLogRawDataDto;
import com.sz21c.config.datasource.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CollectorDao extends BaseDao {

    public void insertMstLogRawData(MstLogRawDataDto param) throws Exception {
        getSqlSession().insert(getQueryStatement("insertLogRawData"), param);
    }
}
