package maths;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ConfusionMatrixTest {

    /**
     * Test Scenario: Application attempts to create a ConfusionMatrix with incorrect number of classes
     * Expected Output: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBuildWithInvalidNumClasses(){

        ConfusionMatrix matrix = new ConfusionMatrix();
        List<Integer> actual = new ArrayList<>();
        List<Integer> predicted = new ArrayList<>();
        int nClasses = 1;
        matrix.buildFrom(actual, predicted, nClasses);
    }

    /**
     * Test Scenario: Application attempts to create a ConfusionMatrix with empty actual data
     * Expected Output: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBuildWithEmptyActualClasses(){

        ConfusionMatrix matrix = new ConfusionMatrix();
        List<Integer> actual = new ArrayList<>();
        List<Integer> predicted = new ArrayList<>();
        int nClasses = 2;
        matrix.buildFrom(actual, predicted, nClasses);
    }

    /**
     * Test Scenario: Application attempts to create a ConfusionMatrix with empty predicted data
     * Expected Output: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBuildWithEmptyPredictedClasses(){

        ConfusionMatrix matrix = new ConfusionMatrix();
        List<Integer> actual = new ArrayList<>();
        actual.add(2);
        List<Integer> predicted = new ArrayList<>();
        int nClasses = 2;
        matrix.buildFrom(actual, predicted, nClasses);
    }

    /**
     * Test Scenario: Application attempts to create a ConfusionMatrix with different sizes for
     *                actual and predicted classes
     * Expected Output: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBuildWithDifferentSizes(){

        ConfusionMatrix matrix = new ConfusionMatrix();
        List<Integer> actual = new ArrayList<>();
        actual.add(2);
        actual.add(2);
        List<Integer> predicted = new ArrayList<>();
        predicted.add(1);
        int nClasses = 2;
        matrix.buildFrom(actual, predicted, nClasses);
    }

    /**
     * Test Scenario:   Application supplies class index outside the range [0, nClasses)
     * Expected Output: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void buildFromInvalidValidInput(){

        ConfusionMatrix matrix = new ConfusionMatrix();
        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(2);
        List<Integer> predicted = new ArrayList<>();
        predicted.add(1);
        predicted.add(2);
        int nClasses = 2;
        matrix.buildFrom(actual, predicted, nClasses);
    }


    /**
     * Test Scenario: Application predicts the same classes as the actual classes
     * Expected Output: True positives then should be the same the actual classes
     */
    @Test
    public void buildFromValidInput(){

        ConfusionMatrix matrix = new ConfusionMatrix();
        List<Integer> actual = new ArrayList<>();
        actual.add(0);
        actual.add(1);
        List<Integer> predicted = new ArrayList<>();
        predicted.add(0);
        predicted.add(1);
        int nClasses = 2;
        matrix.buildFrom(actual, predicted, nClasses);
        assertEquals(matrix.truePositives(), actual.size());

    }
}
