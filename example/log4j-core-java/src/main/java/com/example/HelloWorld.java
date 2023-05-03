package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {

    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<20;i++) {
            logger.debug("Debug log message");
            logger.info("Info log message");
            logger.error("Error log message");
            Thread.sleep(1000);
        }
    }

}
