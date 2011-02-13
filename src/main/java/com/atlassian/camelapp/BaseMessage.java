package com.atlassian.camelapp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright Atlassian: 10/02/11
 */
@XmlRootElement(namespace = "camelapp")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseMessage
{
    private Integer value;

    public BaseMessage()
    {
    }

    public BaseMessage(Integer value)
    {
        this.value = value;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "BaseMessage: description = '" + value + '\'';
    }
}
