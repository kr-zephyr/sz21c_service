package com.sz21c.logger.slacksender.model;

import lombok.Data;

@Data
public class SlackMessageModel {
    private String channel;
    private String username;
    private String text;
}
