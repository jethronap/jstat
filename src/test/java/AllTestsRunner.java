import org.junit.runner.Result;

import dataloader.CsvLoaderTestRunner;
import testutils.ITestRunnerBase;
import dataloader.JsonLoaderTestRunner;
import datastructs.NumericSampleTestRunner;
import stats.ResampleTestRunner;
import utils.ArrayOperationsTestRunner;


public class AllTestsRunner {


    public static void main(String[] args) {

        int totalNumTests = 0;
        int totalFailureCount = 0;
        int totalIgnoredCount = 0;
        long totalTime = 0;

        ITestRunnerBase runner = new ResampleTestRunner();
        Result result = runner.run(args);

        totalNumTests += result.getRunCount();
        totalFailureCount += result.getFailureCount();
        totalIgnoredCount += result.getIgnoreCount();
        totalTime += result.getRunTime();

        runner = new ArrayOperationsTestRunner();
        result = runner.run(args);

        totalNumTests += result.getRunCount();
        totalFailureCount += result.getFailureCount();
        totalIgnoredCount += result.getIgnoreCount();
        totalTime += result.getRunTime();

        runner = new NumericSampleTestRunner();
        result = runner.run(args);

        totalNumTests += result.getRunCount();
        totalFailureCount += result.getFailureCount();
        totalIgnoredCount += result.getIgnoreCount();
        totalTime += result.getRunTime();

        runner = new JsonLoaderTestRunner();
        result = runner.run(args);

        totalNumTests += result.getRunCount();
        totalFailureCount += result.getFailureCount();
        totalIgnoredCount += result.getIgnoreCount();
        totalTime += result.getRunTime();

        runner = new CsvLoaderTestRunner();
        result = runner.run(args);

        totalNumTests += result.getRunCount();
        totalFailureCount += result.getFailureCount();
        totalIgnoredCount += result.getIgnoreCount();
        totalTime += result.getRunTime();

        System.out.println("Total Statistics: ");
        System.out.println("Total Number of Tests Executed: " + totalNumTests);
        System.out.println("Total Number of Failed Tests: "+totalFailureCount);
        System.out.println("Total Number of Ignored Tests: "+totalIgnoredCount);
        System.out.println("Total Test Suite Running Time: "+totalTime+" secs");
    }
}
