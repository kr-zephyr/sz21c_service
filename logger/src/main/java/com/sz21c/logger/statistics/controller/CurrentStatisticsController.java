package com.sz21c.logger.statistics.controller;

import com.sz21c.logger.statistics.model.BldevVisitGooglebotCntDto;
import com.sz21c.logger.statistics.model.DailyStatisticsModel;
import com.sz21c.logger.statistics.model.TopTenHitPostDto;
import com.sz21c.logger.statistics.model.TopTenHitPostModel;
import com.sz21c.logger.statistics.service.CurrentStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CurrentStatisticsController {

    @Autowired
    private CurrentStatisticsService currentStatisticsService;

    @GetMapping(value = "/stat/current/visit-googlebot-count", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<BldevVisitGooglebotCntDto> getBldevVisitGoogleBotCnt() throws Exception {
        return currentStatisticsService.getBldevVisitGoogleBotCnt();
    }

    @GetMapping(value = "/stat/current/connectivity", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DailyStatisticsModel getDailyStatistics() throws Exception {
        DailyStatisticsModel dailyStatisticsModel = new DailyStatisticsModel();
        dailyStatisticsModel.setTotPageHit(currentStatisticsService.getCurrentHitTotalCount());
        dailyStatisticsModel.setTotVisitor(currentStatisticsService.getCurrentVisitorCount());

        return dailyStatisticsModel;
    }

    @GetMapping(value = "/stat/current/top-ten-posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<TopTenHitPostModel> getTopTenPostList() throws Exception {
        List<TopTenHitPostDto> list = currentStatisticsService.getCurrentTopTenHitPost();

        List<TopTenHitPostModel> rtnList = new ArrayList<>();
        for(TopTenHitPostDto dto : list) {
            rtnList.add(dto.getTopTenHitPostModel());
        }

        return rtnList;
    }
}
