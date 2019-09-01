package stats;

import datastructs.ISample;
import datastructs.NumericSample;
import org.junit.Test;
import stats.utils.Resample;
import static org.junit.Assert.assertEquals;

public class ResampleTest {


    /**
     * Test Scenario: The application passes a non-null sample as input
     * and a non-null sample instance as output.
     * Expected Output: Resampling should populate the output sample with the given size
     */
    @Test
    public void testResampleEqualSize(){


        ISample<Double> inSample = new NumericSample("TestIn", 10);

        for(int i=0; i<10; ++i){
            inSample.add(new Double(i));
        }

        ISample<Double> outSample = new NumericSample("TestOut", 10);

        Resample.resample(inSample, outSample, 10, 3);
        assertEquals(outSample.getsize(), 10);
    }


    /**
     * Test Scenario: The application passes a non-null sample as input
     * and a non-null sample instance as output.
     * Expected Output: Resampling should populate the output sample with the given size
     */
    @Test
    public void testResampleSmallerSize(){


        ISample<Double> inSample = new NumericSample("TestIn", 10);

        for(int i=0; i<10; ++i){
            inSample.add(new Double(i));
        }

        ISample<Double> outSample = new NumericSample("TestOut", 10);

        Resample.resample(inSample, outSample, 5, 3);
        assertEquals(outSample.getsize(),5 );
    }


}
