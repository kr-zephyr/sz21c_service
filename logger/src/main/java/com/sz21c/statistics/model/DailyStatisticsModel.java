package com.sz21c.statistics.model;

import lombok.Data;

@Data
public class DailyStatisticsModel {
    private Integer totPageHit = 0;
    private Integer totVisitor = 0;
    private Integer dbeVisitor = 0;
    private Integer devopsVisitor = 0;
}
