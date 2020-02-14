package datastructs;

import datasets.VectorDouble;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NumericSampleTest {

    /**
     * Test Scenario: The application passes a List of doubles
     * Expected Output: The given List should be copied
     */
    @Test
    public void testCopy(){

        List<Double> list = new ArrayList<Double>();

        for(int i=0; i<10; ++i){
            list.add(new Double(i));
        }

        VectorDouble sample = new VectorDouble(list);
        assertEquals(sample.size(), list.size());
    }


    /**
     * Test Scenario: The application passes a List of doubles and creates a sample
     * It then triggers the calculation of the statistics. Then adds more elements into the sample
     * Expected Output: The isValid flag should be false
     */
    @Test
    public void testFalsifyAdd(){

        List<Double> list = new ArrayList<Double>();

        for(int i=0; i<10; ++i){
            list.add(new Double(i));
        }


        VectorDouble sample = new VectorDouble(list);

        assertFalse(sample.isStatisticsValid());

        // trigger the computation
        sample.getMean();

        assertTrue(sample.isStatisticsValid());

        // trigger falsify
        sample.add(2,100.0);
        assertFalse(sample.isStatisticsValid());
    }

    /**
     * Test Scenario: The application passes a List of doubles and creates a sample
     * It then triggers the calculation of the statistics. Then sets one or more elements into the sample
     * to a different value
     * Expected Output: The isValid flag should be false
     */
    @Test
    public void testFalsifySet(){

        List<Double> list = new ArrayList<Double>();

        for(int i=0; i<10; ++i){
            list.add(new Double(i));
        }


        VectorDouble sample = new VectorDouble(list);

        assertFalse(sample.isStatisticsValid());

        // trigger the computation
        sample.getMean();

        assertTrue(sample.isStatisticsValid());

        // trigger falsify
        sample.set(2, 100.0);
        assertFalse(sample.isStatisticsValid());

    }
}
