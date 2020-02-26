package maths;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import testutils.ITestRunnerBase;

public class ConfusionMatrixTestRunner implements ITestRunnerBase {

    public Result run(String[] args){

        System.out.println("============================");
        System.out.println("Start executing ConfusionMatrixTest tests");

        Result result = JUnitCore.runClasses(ConfusionMatrixTest.class);

        if( !result.wasSuccessful()) {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
        else{
            System.out.println("\tAll tests passed: "+ result.getRunCount());
        }

        System.out.println("\tTest run time: "+ result.getRunTime());
        System.out.println("Done....");
        System.out.println("============================");

        return result;

    }

    public static void main(String[] args){
        ConfusionMatrixTestRunner runner = new ConfusionMatrixTestRunner();
        runner.run(args);

    }
}
