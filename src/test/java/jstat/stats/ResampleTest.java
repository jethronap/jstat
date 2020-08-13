package jstat.stats;

import jstat.datasets.VectorDouble;
import jstat.datastructs.IVector;
import org.junit.Ignore;
import org.junit.Test;
import jstat.stats.utils.Resample;

import static org.junit.Assert.*;

public class ResampleTest {


    static final VectorDouble getNumericSample(int size){

        VectorDouble inSample = new VectorDouble(size);

        for(int i=0; i<inSample.size(); ++i){
            inSample.set(i, new Double(i));
        }

        return inSample;
    }


    /**
     * Test Scenario: The application passes a non-null sample as input
     * and a non-null sample instance as output.
     * Expected Output: Resampling should populate the output sample with the given size
     */
    @Test
    public void testResampleEqualSize(){

        VectorDouble inSample = ResampleTest.getNumericSample(10);
        IVector<Double> outSample = Resample.resample(inSample, 10, 3);
        assertNotNull(outSample);
        assertEquals(outSample.size(), 10);
    }


    /**
     * Test Scenario: The application passes a non-null sample as input. A smaller resampled sample is requested
     * Expected Output: Resampling should populate the output sample with the given size
     */
    @Test
    public void testResampleSmallerSize(){

        VectorDouble inSample = ResampleTest.getNumericSample(10);
        IVector<Double> outSample = Resample.resample(inSample, 5, 3);
        assertNotNull(outSample);
        assertEquals(outSample.size(),5 );
    }


    /**
     * Test Scenario: The application passes a non-null sample as input. A larger resampled sample is requested
     * Expected Output: Resampling should populate the output sample with the given size
     */
    @Test
    @Ignore
    public void testResampleLargerSize(){
        assertFalse("Fix this test by adding support", true);
    }


}
