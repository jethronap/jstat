package utils;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


public class ArrayOperationsTest {


    private final ArrayList<Double> getSample(int size){

        final ArrayList<Double> sample = new ArrayList<Double>();

        for(int i=0; i<size; ++i){
            sample.add( new Double(i));
        }

        return sample;
    }


    /**
     * Test Scenario: The application passes a null sample to compute its max
     * Expected Output: Should throw an NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testNullMax(){

        ArrayOperations.max(null);
    }

    /**
     * Test Scenario: The application passes a non-null sample to compute its min
     * Expected Output: Should throw an NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testNullMin(){

        ArrayOperations.min(null );;
    }

    /**
     * Test Scenario: The application passes a non-null sample to compute its max
     * Expected Output: Should correctly compute maximum
     */
    @Test
    public void testInvalidEndPosiionSampleMean(){

        final ArrayList<Double> sample = getSample(4);
        Double max = ArrayOperations.max(sample);
        assertEquals(max.intValue(), 3);
    }

    /**
     * Test Scenario: The application passes a non-null sample to compute its min
     * Expected Output: Correct min is computed
     */
    @Test
    public void testCorrectComputeSampleMean(){

        final ArrayList<Double> sample = getSample(4);
        Double min = ArrayOperations.min(sample );
        assertEquals(min.intValue(), 0);
    }

}
