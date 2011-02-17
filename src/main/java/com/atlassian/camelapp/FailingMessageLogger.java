package com.atlassian.camelapp;

import org.apache.camel.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
* Copyright Atlassian: 4/02/11
*/
public class FailingMessageLogger
{
    private Log log = LogFactory.getLog(this.getClass());

    @Handler
    public void handleMsg(@Body BaseMessage msg,
                          @Header(MessageRetryTagger.ATTEMPT_HEADER) Integer attempts)
    {
        log.info("HANDLER Received delivery attempt "+(attempts == null ? 1 : attempts+1)+": "+msg);

        if (msg.getValue() == 5) {
            log.warn("Throwing permanent-failure exception");
            throw new PermanentFailureException();

        } else if (msg.getValue() == 10) {
            log.warn("Throwing temporary-failure exception");
            throw new TemporaryFailureException();
        } else {
            log.info("Delivered");
        }
    }
}
