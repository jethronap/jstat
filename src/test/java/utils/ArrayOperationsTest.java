package utils;
import utils.ArrayOperations;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class ArrayOperationsTest {


    private final ArrayList<Integer> getSample(int size){

        final ArrayList<Integer> sample = new ArrayList<Integer>();

        for(int i=0; i<size; ++i){
            sample.add(i);
        }

        return sample;
    }


    /**
     * Test Scenario: The application passes a null sample to compute its max
     * Expected Output: Should throw an NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testNullMax(){

        ArrayOperations.max(null, new Integer(0));
    }

    /**
     * Test Scenario: The application passes a non-null sample to compute its min
     * Expected Output: Should throw an NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testNullMin(){

        ArrayOperations.min(null, new Integer(0));;
    }

    /**
     * Test Scenario: The application passes a non-null sample to compute its max
     * Expected Output: Should correctly compute maximum
     */
    @Test
    public void testInvalidEndPosiionSampleMean(){

        final ArrayList<Integer> sample = getSample(4);
        Integer max = ArrayOperations.max(sample, new Integer(0));
        assertEquals(max.intValue(), 3);
    }

    /**
     * Test Scenario: The application passes a non-null sample to compute its min
     * Expected Output: Correct min is computed
     */
    @Test
    public void testCorrectComputeSampleMean(){

        final ArrayList<Integer> sample = getSample(4);
        Integer min = ArrayOperations.min(sample, new Integer(0));
        assertEquals(min.intValue(), 0);
    }


}
