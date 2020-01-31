package maths;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Unit test for the datastructs.maths.Vector class
 */
public class VectorTest {


    /**
     * Test Scenario: The application initializes a Vector with size and val
     * Expected Output: Vector instance should be properly initialized
     */
    @Test
    public void testVectorConstructor(){

        Vector vec = new Vector(10, 0.0);
        assertEquals(vec.size(), 10);
    }

    /**
     * Test Scenario: The application initializes a Vector with size zero and val
     * Expected Output: Vector should throw IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testVectorConstructorWithZeroSize(){

        Vector vec = new Vector(0, 0.0);
    }

    /**
     * Test Scenario: The application attempts to zero a non initialized Vector
     * Expected Output: Vector should throw IllegalStateException
     */
    @Test(expected = IllegalStateException.class)
    @Ignore
    public void testZeroNonInitializedVector(){
        Vector vec = new Vector();
        vec.zero();
    }

    /**
     * Test Scenario: The application creates an empty vector and then calls resize
     * Expected Output: Vector instance should be resized properly
     */
    @Test
    public void testResizeVector(){
        Vector vec = new Vector();
        vec.resize(10);
        assertEquals(vec.size(), 10);
    }

    /**
     * Test Scenario: The application creates a vector with given size and then calls resize with smaller size
     * Expected Output: Vector instance should be resized properly
     */
    @Test
    public void testResizeWithSmallerSizeVector(){
        Vector vec = new Vector(10, 0.0);
        vec.resize(5);
        assertEquals(vec.size(), 5);
    }

    /**
     * Test Scenario: The application creates a vector with given size and then calls resize with larger size
     * Expected Output: Vector instance should be resized properly
     */
    @Test
    public void testResizeWithLargerSizeVector(){
        Vector vec = new Vector(10, 0.0);
        vec.resize(15);
        assertEquals(vec.size(), 15);
    }



}



