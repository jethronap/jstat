package testutils;

import org.junit.runner.Result;

public interface ITestRunnerBase {

    /**
     * Run the tests for this test runner
     *
     * @param args The arguments
     * @return The result
     */
    Result run(String[] args);
}
