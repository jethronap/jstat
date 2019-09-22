package dataloader;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import testutils.ITestRunnerBase;

public class JsonLoaderTestRunner implements ITestRunnerBase {


    public Result run(String[] args){

        System.out.println("=====================================================");
        System.out.println("Running: "+ JsonLoaderTest.class.getName()+ " tests");

        Result result = JUnitCore.runClasses(JsonLoaderTest.class);

        if( !result.wasSuccessful()) {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
        else{
            System.out.println("All tests passed: "+ result.getRunCount());
        }

        System.out.println("Test run time: "+ result.getRunTime()+" secs");
        System.out.println("Done...");
        System.out.println("=====================================================");

        return result;
    }

    public static void main(String[] args) {

        JsonLoaderTestRunner runner = new JsonLoaderTestRunner();
        runner.run(args);
    }
}
