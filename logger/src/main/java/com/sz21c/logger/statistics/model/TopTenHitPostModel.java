package com.sz21c.logger.statistics.model;

import lombok.Data;

@Data
public class TopTenHitPostModel {
    private String blogType;
    private String title;
    private Integer viewCount;
}
