package com.atlassian.camelapp;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Headers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Copyright Atlassian: 16/02/11
 */
public class MessageRetryTagger
{
    private Log log = LogFactory.getLog(this.getClass());


    public static final String ATTEMPT_HEADER = "com.atlassian.hams.attempts";

    @Handler
    public void handleMsg(@Body BaseMessage msg,
                          @Headers Map<String, Object> headers)
    {
        log.info("TAGGER Received: "+msg);

        Integer attempts = (Integer)headers.get(ATTEMPT_HEADER);
        if (attempts == null)
            attempts = 1;
        else
            attempts++;

        log.warn("This is delivery attempt "+attempts);
        headers.put(ATTEMPT_HEADER, attempts);
    }

}
