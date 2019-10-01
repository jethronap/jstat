package utils;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class TableOperations {

    public static double[][] getTableColumnsForRegressionMatrix(Table dataSet, String[] names, int nRows){

        double[][] matrix = new double[nRows][names.length +1 ];

        for(int i=0; i < matrix.length; ++i){

            matrix[i][0] = 1.0;
        }

        int rowIdx = 0;
        for(String name:names){

            DoubleColumn y = dataSet.doubleColumn(name);

            //matrix[colIdx] = new double[y.size()];
            double[] vals = y.asDoubleArray();

            for(int c = 1; c < matrix[rowIdx].length; ++c)

                matrix[rowIdx][c] = vals[c-1];
                rowIdx++;
        }

        return matrix;
    }
}
