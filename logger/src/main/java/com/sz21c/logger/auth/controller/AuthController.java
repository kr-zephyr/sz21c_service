package com.sz21c.logger.auth.controller;

import com.sz21c.logger.config.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class AuthController {

    @Value("${logger.authorization.value}")
    private String authValue;

    @Value("${sz21c.cookie.domain}")
    private String cookieDomain;

    @GetMapping(value = "/{auth}")
    public void createSession(HttpServletResponse response, @PathVariable String auth) throws Exception {
        if(authValue.equals(auth.replace("c-session-", ""))) {
            Cookie cookie = new Cookie(CommonConstants.EXCEPTED_LOG_NAME, authValue);
            cookie.setDomain(cookieDomain);
            cookie.setMaxAge(60 * 60 * 24 * 365 * 10);  //10년으로 셋팅
            response.addCookie(cookie);

            log.info("set cookie!!!");
        }
    }

    @GetMapping(value = "/chk-auth/{exceptedLogValue}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getAuthByCookie(@PathVariable String exceptedLogValue, HttpServletResponse response) {
        if(authValue.equals(exceptedLogValue)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        log.info("exceptedLogValue :: " + exceptedLogValue);
        log.info("status :: " + response.getStatus());

        return null;
    }
}
