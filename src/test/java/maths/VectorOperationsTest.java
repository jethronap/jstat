package maths;


import datasets.VectorDouble;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VectorOperationsTest {


    /**
     * Test Scenario: The application initializes two Vectors of unequal size and requets their dot product
     * Expected Output: VectorOperations should throw IllegalStateException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testVectorConstructorWithZeroSize(){

        VectorDouble v1 = new VectorDouble(0, 0.0);
        VectorDouble v2 = new VectorDouble(10, 0.0);
        VectorOperations.dotProduct(v1, v2);
    }

    /**
     * Test Scenario: The application adds two vectors of different size
     * Expected Output: VectorOperations should throw IllegalStateException
     */
    @Test(expected = IllegalStateException.class)
    public void testUnequalVectorAdd(){

        VectorDouble v1 = new VectorDouble(20, 0.0);
        VectorDouble v2 = new VectorDouble(10, 0.0);
        VectorOperations.add(v1, v2);
    }
}
