package com.sz21c.collector.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Data
public class LoggerParamModel {
    private String referrerUrl;
    private String clientIp;
    private String url;
    private String userAgent;
    private String locale;
    private String logType;
    private String urlTitle;


    public MstLogRawDataDto getMstLogRawDataDto() {
        MstLogRawDataDto mstLogRawDataDto = new MstLogRawDataDto();
        mstLogRawDataDto.setUrl(this.url);
        mstLogRawDataDto.setUserAgent(this.userAgent);
        mstLogRawDataDto.setLocale(this.locale);
        mstLogRawDataDto.setReferrerIp(this.clientIp);
        mstLogRawDataDto.setReferrerUrl(this.referrerUrl);
        mstLogRawDataDto.setType(this.logType);

        mstLogRawDataDto.setUrlTitle(this.urlTitle);
        if(this.urlTitle == null || "".equals(this.urlTitle)) {
            if("https://kr-zephyr.github.io/".equals(this.url)) {
                mstLogRawDataDto.setUrlTitle("devops root");
            }
        }

        String calledUrl = mstLogRawDataDto.getUrl();
        try {
            URL url = new URL(calledUrl);
            calledUrl = url.getHost();

            switch(calledUrl) {
                case "www.sz21c.com" :
                case "blog.sz21c.com" :
                case "sz21c.tistory.com" :
                    mstLogRawDataDto.setSiteType(SiteTypeEnum.BLTIS.toString());
                    break;
                case "kr-zephyr.github.io" :
                    mstLogRawDataDto.setSiteType(SiteTypeEnum.BLDEV.toString());
                    break;
                default :
                    mstLogRawDataDto.setSiteType("ETC");
            }
        } catch (MalformedURLException mue) {
            log.error("Error parsing domain :: " + calledUrl);
            log.error(mue.getStackTrace().toString());
        }

        return mstLogRawDataDto;
    }
}
