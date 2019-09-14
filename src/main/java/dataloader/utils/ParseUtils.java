package dataloader.utils;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {


    /**
     * Parse the entries of the given list of strings as doubles
     */
    public static List< Double > parseAsDouble(List<String> dataSet){

        List<Double> data = new ArrayList<Double>( dataSet.size());

        for (String entry: dataSet) {
            data.add( Double.parseDouble(entry));

        }

        return data;
    }



    /**
     * Parse the entries of the given list of strings as integers
     */
    public static List< Integer > parseAsInteger(List<String> dataSet){

        List<Integer> data = new ArrayList<Integer>( dataSet.size());

        for (String entry: dataSet) {
            data.add( Integer.parseInt(entry));
        }
        return data;
    }
}
