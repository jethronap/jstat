package utils;

import dataloader.utils.ParseUtils;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;

import java.util.List;

public class TableOperations {

    public static double[][] getTableColumnsForRegressionMatrix(Table dataSet, String[] names, int nRows){

        double[][] matrix = new double[nRows][names.length  ];

        int colIdx = 0;
        for(String name:names) {

            Column col = dataSet.column(name);

            double[] vals = ParseUtils.parseAsDoubleArray(col);

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
