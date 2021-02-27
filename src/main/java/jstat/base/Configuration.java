package jstat.base;

import java.io.File;

public class Configuration {

    public static boolean ENABLE_WARNINGS = true;
    public static String dataDirectory = "src/main/resources/jstat/datasets/";

    public static class Logging
    {
        public static String WARN_MSG = "WARNING: ";
        public static void printWarning(String warningMsg){
            System.out.println(WARN_MSG + warningMsg);
        }
    }
}
