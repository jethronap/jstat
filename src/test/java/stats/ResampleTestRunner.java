package stats;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ResampleTestRunner {


    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(ResampleTest.class);

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
}

