package com.sz21c.logger.statistics.scheduler;

import com.sz21c.logger.slacksender.SlackSender;
import com.sz21c.logger.statistics.model.TopTenHitPostDto;
import com.sz21c.logger.statistics.service.DailyScheduleService;
import com.sz21c.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class DailyScheduleTask {

    @Autowired
    DailyScheduleService dailyScheduleService;

    /**
     * 주의....까지는 아니지만...
     * SpringBoot에서 @EnableScheduling을 선언하고 @Scheduled를 사용하는 경우 ScheduledAnnotationBeanPostProcessor로부터
     * inishRegistration() 메소드가 실행되는데, 이 메소드는 TaskScheduler나 ScheduledExecutorService를 찾아 Bean으로 등록한다.
     *
     * 만약 위의 두 Class가 없는 경우에는 fallback 메시지로 아래의 메시지를 반환한다.
     * - Could not find default TaskScheduler bean
     * - Could not find default ScheduledExecutorService bean
     *
     * 위의 메시지는 Debug로 선언된 메시지이며, 무시해도 무방하다.
     *
     * 참고 : http://stackoverflow.com/questions/31199888/spring-task-scheduler-no-qualifying-bean-of-type-org-springframework-scheduli
     *
     * @throws Exception
     */
    @Scheduled(cron = "0 0 8 * * ?")
//    @Scheduled(cron = "0 * * * * ?")
    public void sendReportToSlack() throws Exception {
        log.info("[일간 레포트 작성 시작]");

        List<TopTenHitPostDto> topTenHitPostList = dailyScheduleService.getDailyTopTenHitPost();

        StringBuffer sb = new StringBuffer();
        sb.append(DateTimeUtil.getYestdayForKorean() + " 블로그 접속 현황\n");
        sb.append("포스트 hit 수 : " + dailyScheduleService.getDailyHitTotalCount() + "\n");
        sb.append("방문자 수 : " + dailyScheduleService.getDailyVisitorCount() + "\n\n");
        sb.append("어제 hit 수 높은 post Top 10\n");

        AtomicInteger idx = new AtomicInteger();
        topTenHitPostList.forEach((TopTenHitPostDto data)
            -> sb.append((idx.incrementAndGet() + 1) + ". [" + data.getCount() + "] " + data.getUrlTitle() + "\n"));

        log.info(sb.toString());

        SlackSender slackSender = new SlackSender(sb.toString());
        slackSender.sendStatistics();

        log.info("[일간 레포트 작성 완료]");
    }
}
