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
public class ExpiredMessageLogger
{
    private Log log = LogFactory.getLog(this.getClass());

    @Handler
    public void handleMsg(@Body BaseMessage msg, @Header(Exchange.REDELIVERY_COUNTER) Integer retries) {
        log.info("Received an expired message after "+ (retries == null ? 0 : retries) +" retries: "+msg);
    }
}
