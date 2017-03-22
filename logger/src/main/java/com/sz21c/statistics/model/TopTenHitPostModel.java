package com.sz21c.statistics.model;

import lombok.Data;

@Data
public class TopTenHitPostModel {
    private String blogType;
    private String title;
    private Integer viewCount;
}
