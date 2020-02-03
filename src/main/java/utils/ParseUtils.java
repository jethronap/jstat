package utils;

import tech.tablesaw.api.ColumnType;
import tech.tablesaw.columns.Column;

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
     * Parse the entries of the given Column  as doubles
     */
    public static List< Double > parseAsDouble(Column dataSet){

        List<Double> data =null;
        ColumnType type = dataSet.type();

        if(type.name() == "INTEGER"){

            data = ParseUtils.parseIntegerToDouble(dataSet);
        }
        else if(type.name() == "DOUBLE"){

            data = new ArrayList<Double>( dataSet.size());

            for (int i = 0; i < dataSet.size() ; i++) {
                data.add( (Double) dataSet.get(i));
            }
        }

        return data ;
    }


    public static double[] parseAsDoubleArray(Column dataSet){

        List<Double> listData = ParseUtils.parseAsDouble(dataSet);
        double[] data = new double[listData.size()];

        for (int i = 0; i < data.length; i++) {
            data[i] = listData.get(i);
        }

        return data ;
    }

    public static List< Double > parseIntegerToDouble(Column dataSet){

        List<Double> data = new ArrayList<Double>( dataSet.size());

        for (int i = 0; i < dataSet.size() ; i++) {
            data.add( (double) ((Integer)dataSet.get(i)).intValue());
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
