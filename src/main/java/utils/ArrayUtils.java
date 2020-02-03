package utils;

public class ArrayUtils {

    public static double[] toArray(Double[] data){

        double[] result = new double[data.length];

        for (int i = 0; i <data.length ; i++) {
            result[i] = data[i].doubleValue();
        }

        return result;
    }

    public static double[][] toArray(Double[][] data){

        double[][] result = new double[data.length][];

        for (int i = 0; i <data.length ; i++) {
            result[i] = new double[data[i].length];

            for (int j=0; j<result[i].length; ++j){
                result[i][j] = data[i][j].doubleValue();
            }
        }

        return result;
    }
}
