package com.sz21c.logger.statistics.model;

import lombok.Data;

import java.util.Date;

@Data
public class BldevVisitGooglebotCntDto {
    private String urlTitle;
    private Integer count;
    private Date lastVisitDate;
}
