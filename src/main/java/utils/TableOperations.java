package utils;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class TableOperations {

    public static double[][] getTableColumnsForRegressionMatrix(Table dataSet, String[] names, int nRows){

        double[][] matrix = new double[nRows][names.length  ];

        int colIdx = 0;
        for(String name:names) {

            DoubleColumn x = dataSet.doubleColumn(name);

            double[] vals = x.asDoubleArray();

            if(vals.length != matrix.length){
                throw new IllegalStateException("Invalid sizes");
            }

            for (int rowIdx = 0; rowIdx < matrix.length; ++rowIdx) {
                    matrix[rowIdx][colIdx] = vals[rowIdx];
            }

            colIdx++;
        }

        return matrix;
    }
}
