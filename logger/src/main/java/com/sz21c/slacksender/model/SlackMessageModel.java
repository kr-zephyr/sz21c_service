package com.sz21c.slacksender.model;

import lombok.Data;

@Data
public class SlackMessageModel {
    private String channel;
    private String username;
    private String text;
}
