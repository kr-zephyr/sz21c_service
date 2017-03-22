package com.sz21c.statistics.model;

import lombok.Data;

@Data
public class TopTenHitPostDto {
    private String urlTitle;
    private int count;
    private String siteType;

    public TopTenHitPostModel getTopTenHitPostModel() {
        TopTenHitPostModel topTenHitPostModel = new TopTenHitPostModel();
        topTenHitPostModel.setBlogType(this.siteType);
        topTenHitPostModel.setTitle(this.urlTitle);
        topTenHitPostModel.setViewCount(this.count);

        return topTenHitPostModel;
    }
}
