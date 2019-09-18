package datastructs;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class NumericSampleTestRunner {


    public static void run(String[] args){

        Result result = JUnitCore.runClasses(NumericSampleTest.class);

        if( !result.wasSuccessful()) {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
        else{
            System.out.println("All tests passed: "+ result.getRunCount());
        }

        System.out.println("Test run time: "+ result.getRunTime());

    }

    public static void main(String[] args) {

        NumericSampleTestRunner.run(args);
    }
}
