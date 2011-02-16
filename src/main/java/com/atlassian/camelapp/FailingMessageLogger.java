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
                          @Headers Map headers)
    {
        log.info("Received: "+msg);

        Integer attempts = (Integer)headers.get("com.atlassian.hams.attempts");
        if (attempts == null)
            attempts = 0;

        attempts++;
        log.warn("This is redelivery attempt "+attempts);
        headers.put("com.atlassian.hams.attempts", attempts);


//        if (msg.getValue() == 5) {
//            log.warn("Throwing permanent-failure exception");
//            throw new PermanentFailureException();
//        } else
        if (msg.getValue() >= 10) {
            log.warn("Throwing temporary-failure exception");
            throw new TemporaryFailureException();
        }
    }
}
