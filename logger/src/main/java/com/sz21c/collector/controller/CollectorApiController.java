package com.sz21c.collector.controller;

import com.sz21c.collector.model.LogTypeEnum;
import com.sz21c.collector.model.LoggerParamModel;
import com.sz21c.collector.service.CollectorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@RestController
public class CollectorApiController {

    @Value("${logger.authorization.value}")
    String authValue;

    @Autowired
    private CollectorService collectorService;

    @PostMapping(value = "/logger/put", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putLog(
            HttpServletRequest request
        , @RequestBody LoggerParamModel model)
    throws Exception {
        model.setLocale(request.getLocale().toString());
        model.setLogType(LogTypeEnum.ONLOAD.toString());
        model.setUrl(request.getHeader("referer"));
        model.setUserAgent(request.getHeader("User-Agent"));

        javax.servlet.http.HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = req.getRemoteAddr();

        model.setClientIp(ip);

        if(log.isDebugEnabled()) {
            log.debug("model :: " + model.toString());
        }

        if(!authValue.equals(model.getLoggerExcepted())) {
            collectorService.addLog(model);
        }
    }
}
