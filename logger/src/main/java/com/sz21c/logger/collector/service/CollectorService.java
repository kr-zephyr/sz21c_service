package com.sz21c.logger.collector.service;

import com.sz21c.logger.collector.dao.CollectorDao;
import com.sz21c.logger.collector.model.LoggerParamModel;
import com.sz21c.logger.collector.model.MstLogRawDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value = "collectorService")
public class CollectorService {

    @Autowired
    CollectorDao collectorDao;

    @Transactional
    public void addLog(LoggerParamModel model) throws Exception {
        MstLogRawDataDto dto = model.getMstLogRawDataDto();

        collectorDao.insertMstLogRawData(dto);
    }

}
