package com.home.basic.logging;

public class Test {
    public static void main(String[] args) {
//        LogFactory.useStdOutLogging();
        Log log = LogFactory.getLog(Test.class);
        log.debug("Thank you! \n");
    }
}
