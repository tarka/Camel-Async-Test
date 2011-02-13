package com.atlassian.camelapp;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Copyright Atlassian: 10/02/11
 */
public class InjectingJob
{
    private Log log = LogFactory.getLog(this.getClass());

    @EndpointInject ProducerTemplate inputq;

    Integer count = 1;

    public void doIt() {
        if (count > 15)
            return;

        log.info("Doing injection of "+count);

        inputq.sendBody(new BaseMessage(count++));
    }

}
