package datastructs;

import org.junit.Test;
import datastructs.CategoricalSample;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CategoricalSampleTest {


    /**
     * Test Scenario: The application passes a List of Strings
     * Expected Output: The given List should be copied
     */
    @Test
    public void testCopy(){

        List<String> list = new ArrayList<String>();

        for(int i=0; i<10; ++i){
            list.add("Category_"+i);
        }

        CategoricalSample sample = new CategoricalSample("Test", list);
        assertEquals(sample.size(), 10);
    }

    /**
     * Test Scenario: The application passes a List of Strings indicating 4 different categories
     * Expected Output: The given List should be copied and unique categories should be returned
     */
    @Test
    public void testGetCategories(){

        List<String> list = new ArrayList<String>();

        for(int i=0; i<20; ++i){

            if( i < 5) {
                list.add("Category_1");
            }
            else if( i < 10) {
                list.add("Category_2");
            }
            else if( i < 15) {
                list.add("Category_3");
            }
            else if( i < 20) {
                list.add("Category_4");
            }
        }

        CategoricalSample sample = new CategoricalSample("Test", list);
        assertEquals(sample.size(), 20);
        assertEquals(sample.getCategories().size(), 4);
        assertEquals(sample.getCategoryFrequency("Category_4"), 5);
    }


    /**
     * Test Scenario: The application passes a List of Strings indicating 4 different categories
     * and then adds one more category
     * Expected Output: The given List should be copied and unique categories should be returned
     */
    @Test
    public void testGetCategoriesAfterAddNewCategory(){

        List<String> list = new ArrayList<String>();

        for(int i=0; i<20; ++i){

            if( i < 5) {
                list.add("Category_1");
            }
            else if( i < 10) {
                list.add("Category_2");
            }
            else if( i < 15) {
                list.add("Category_3");
            }
            else if( i < 20) {
                list.add("Category_4");
            }
        }

        CategoricalSample sample = new CategoricalSample("Test", list);
        assertEquals(sample.size(), 20);
        assertEquals(sample.getCategories().size(), 4);
        assertEquals(sample.getCategoryFrequency("Category_4"), 5);

        // add one more category
        sample.add("Category_5");

        assertEquals(sample.getCategories().size(), 5);
        assertEquals(sample.getCategoryFrequency("Category_4"), 5);
        assertEquals(sample.getCategoryFrequency("Category_5"), 1);
    }


    /**
     * Test Scenario: The application passes a List of Strings indicating 4 different categories
     * and then sets one  sample entry to some other category
     * Expected Output: The given List should be copied and unique categories should be returned
     */
    @Test
    public void testGetCategoriesAfterSetNewCategory(){

        List<String> list = new ArrayList<String>();

        for(int i=0; i<20; ++i){

            if( i < 5) {
                list.add("Category_1");
            }
            else if( i < 10) {
                list.add("Category_2");
            }
            else if( i < 15) {
                list.add("Category_3");
            }
            else if( i < 20) {
                list.add("Category_4");
            }
        }

        CategoricalSample sample = new CategoricalSample("Test", list);
        assertEquals(sample.size(), 20);
        assertEquals(sample.getCategories().size(), 4);
        assertEquals(sample.getCategoryFrequency("Category_1"), 5);

        // add one more category
        sample.set(6, "Category_5");

        //sample.printInfo();

        sample.getCategories();

        assertEquals(sample.getCategories().size(), 5);
        assertEquals(sample.getCategoryFrequency("Category_1"), 4);
        assertEquals(sample.getCategoryFrequency("Category_5"), 1);
    }





}
