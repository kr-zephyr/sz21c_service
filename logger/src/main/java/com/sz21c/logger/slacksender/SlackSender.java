package com.sz21c.logger.slacksender;

import com.sz21c.logger.slacksender.model.SlackMessageModel;
import com.sz21c.logger.slacksender.model.SlackServerStatChannelModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class SlackSender {

    private String text;

    public SlackSender(String text) {
        this.text = text;
    }

    public void sendStatistics() throws Exception {
        SlackMessageModel message = new SlackMessageModel();
        message.setChannel(SlackServerStatChannelModel.CHANNEL_NAME);
        message.setUsername(SlackServerStatChannelModel.BOT_NAME_STATISTICS);
        message.setText(this.text);

        RestTemplate restTemplate = new RestTemplate();
        String rtn = restTemplate.postForObject(SlackServerStatChannelModel.SLACK_WEB_HOOK_URL, message, String.class);
        log.info("message sent to slack :: " + rtn);
    }
}
