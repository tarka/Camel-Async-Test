package com.atlassian.camelapp;

import org.apache.activemq.ScheduledMessage;
import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Headers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import java.util.Map;

/**
 * Copyright Atlassian: 16/02/11
 */
public class AMQDelayTagger
{
    private Log log = LogFactory.getLog(this.getClass());


    private Integer delaySeconds;

    @Handler
    public void handleMsg(@Body BaseMessage msg,
                          @Headers Map<String, Object> headers)
    {
        log.warn("Tagging message '"+msg+"' to retry at in " + delaySeconds + " seconds");
        headers.put(ScheduledMessage.AMQ_SCHEDULED_DELAY, delaySeconds * 1000);
    }

    @Required
    public void setDelaySeconds(Integer delaySeconds)
    {
        this.delaySeconds = delaySeconds;
    }
}
