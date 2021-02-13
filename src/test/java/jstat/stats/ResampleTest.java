package jstat.stats;

import org.junit.Ignore;
import org.junit.Test;
import jstat.stats.utils.Resample;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import static org.junit.Assert.*;

public class ResampleTest {


    static final INDArray getNumericSample(int size){

        INDArray inSample = Nd4j.zeros(size);

        for(int i=0; i<inSample.size(0); ++i){
            inSample.putScalar(i, (double) i);
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

        INDArray inSample = ResampleTest.getNumericSample(10);
        INDArray outSample = Resample.resample(inSample, 10, 3);
        assertNotNull(outSample);
        assertEquals(outSample.size(0), 10);
    }


    /**
     * Test Scenario: The application passes a non-null sample as input. A smaller resampled sample is requested
     * Expected Output: Resampling should populate the output sample with the given size
     */
    @Test
    public void testResampleSmallerSize(){

        INDArray inSample = ResampleTest.getNumericSample(10);
        INDArray outSample = Resample.resample(inSample, 5, 3);
        assertNotNull(outSample);
        assertEquals(outSample.size(0),5 );
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
