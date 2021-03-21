package jstat.dataloader;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import testutils.ITestRunnerBase;

public class CSVDataLoaderTestRunner implements ITestRunnerBase {

    public Result run(String[] args){

        System.out.println("============================");
        System.out.println("Start executing CSVDataLoader tests");

        Result result = JUnitCore.runClasses(CSVDataLoaderTest.class);

        if( !result.wasSuccessful()) {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
        else{
            System.out.println("\tAll tests passed: "+ result.getRunCount());
        }

        System.out.println("\tTest run time: "+ result.getRunTime() + " secs");
        System.out.println("Done....");
        System.out.println("============================");

        return result;

    }

    public static void main(String[] args){
        CSVDataLoaderTestRunner runner = new CSVDataLoaderTestRunner();
        runner.run(args);

    }
}
