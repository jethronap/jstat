package testutils;

import org.junit.runner.Result;

public interface ITestRunnerBase {

    /**
     * Run the tests for this test runner
     */
    Result run(String[] args);
}
