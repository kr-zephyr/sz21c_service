package com.sz21c.logger.statistics.controller;

import com.sz21c.logger.statistics.model.DailyStatisticsModel;
import com.sz21c.logger.statistics.model.TopTenHitPostDto;
import com.sz21c.logger.statistics.model.TopTenHitPostModel;
import com.sz21c.logger.statistics.service.DailyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DailyStatisticsController {

    @Autowired
    private DailyScheduleService dailyScheduleService;

    @GetMapping(value = "/stat/daily/connectivity", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DailyStatisticsModel getDailyStatistics() throws Exception {
        DailyStatisticsModel dailyStatisticsModel = new DailyStatisticsModel();
        dailyStatisticsModel.setTotPageHit(dailyScheduleService.getDailyHitTotalCount());
        dailyStatisticsModel.setTotVisitor(dailyScheduleService.getDailyVisitorCount());

        return dailyStatisticsModel;
    }

    @GetMapping(value = "/stat/daily/top-ten-posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<TopTenHitPostModel> getTopTenPostList() throws Exception {
        List<TopTenHitPostDto> list = dailyScheduleService.getDailyTopTenHitPost();

        List<TopTenHitPostModel> rtnList = new ArrayList<>();
        for(TopTenHitPostDto dto : list) {
            rtnList.add(dto.getTopTenHitPostModel());
        }

        return rtnList;
    }
}
