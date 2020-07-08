package com.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.Properties;

public class Log4J2PropertiesConfTest {

    private static Logger LOGGER = null;

    @BeforeClass
    public static void setLogger() throws MalformedURLException
    {
        //System.setProperty("log4j.configurationFile","log4j2.xml");
        LOGGER = LogManager.getLogger();
    }
    @Test
    public void testPerformSomeTask() throws Exception {
        Log4J2PropertiesConf log4J2PropertiesConf=new Log4J2PropertiesConf();
        log4J2PropertiesConf.performSomeTask();
    }
}
