package com.sz21c.logger.collector.model;

import lombok.Data;

import java.util.Date;

@Data
public class MstLogRawDataDto {
    private int id;
    private String siteType;
    private String type;
    private String url;
    private String referrerUrl;
    private String referrerIp;
    private String locale;
    private String userAgent;
    private Date createDate;
    private String urlTitle;
}
