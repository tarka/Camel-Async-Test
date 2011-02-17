package com.atlassian.camelapp;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
* Copyright Atlassian: 4/02/11
*/
public class ExpiredMessageLogger
{
    private Log log = LogFactory.getLog(this.getClass());

    @Handler
    public void handleMsg(@Body BaseMessage msg,
                          @Header(MessageRetryTagger.ATTEMPT_HEADER) Integer attempts)
    {
        log.info("Received an expired message after "+ (attempts == null ? 0 : attempts) +" attempts: "+msg);
    }
}
