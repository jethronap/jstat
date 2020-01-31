package maths;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VectorOperationsTest {


    /**
     * Test Scenario: The application initializes two Vectors of unequal size and requets their dot product
     * Expected Output: VectorOperations should throw IllegalStateException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testVectorConstructorWithZeroSize(){

        Vector v1 = new Vector(0, 0.0);
        Vector v2 = new Vector(10, 0.0);
        VectorOperations.dotProduct(v1, v2);
    }

    /**
     * Test Scenarion: The application adds two vectors of different size
     * Expected Output: VectorOperations should throw IllegalStateException
     */
    @Test(expected = IllegalStateException.class)
    public void testUnequalVectorAdd(){

        Vector v1 = new Vector(20, 0.0);
        Vector v2 = new Vector(10, 0.0);
        VectorOperations.add(v1, v2);
    }
}
