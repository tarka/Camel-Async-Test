package com.atlassian.camelapp;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* Copyright Atlassian: 4/02/11
*/
public class FailingMessageLogger
{
    private Log log = LogFactory.getLog(this.getClass());

    @Handler
    public void handleMsg(@Body BaseMessage msg,
                          @Header(Exchange.REDELIVERY_COUNTER) Integer tryCount)
    {
        log.info("Received: "+msg);
        if (tryCount != null)
            log.warn("This is redelivery attempt "+tryCount);
        
        if (msg.getValue() == 5) {
            log.warn("Throwing permanent-failure exception");
            throw new PermanentFailureException();
        } else if (msg.getValue() == 10) {
            log.warn("Throwing temporary-failure exception");
            throw new TemporaryFailureException();
        }
    }
}
