package jstat.datastructs;

import jstat.parallel.partitioners.IPartitionPolicy;

public interface I2DDataSet<RowType> {

    /**
     * Copy from the given IDataSetWrapper
     *
     * @return A data set
     */
    default I2DDataSet copy(){
        throw new IllegalArgumentException("Not implemented");
    }

    /**
     * Build a new instance of this class
     *
     * @param m The rows
     * @param n The columns
     * @return A 2D data set
     */
    default I2DDataSet create(int m, int n){
        throw new IllegalCallerException("No implemented");
    }


    /**
     * How many rows the data set has
     *
     * @return The rows
     */
    int m();

    /**
     * How many columns the data set has
     *
     * @return The columns
     */
    int n();

    /**
     * Returns the i-th row of the data set
     *
     * @param i The number of the row
     * @return The i-th row
     */
    RowType getRow(int i);


    /**
     * Returns the submatrix representation
     *
     * @param <E> A 2D array type
     * @param elements A 2D array
     * @param numColsToInclude Number of columns to include
     * @param includeCols Number of included columns
     */
    <E> void getSubMatrix(E[][] elements, int numColsToInclude, int... includeCols);


    /**
     * Set the row-th row  of the data set
     *
     * @param row Te number of the row
     * @param item The row type
     */
    void set(int row, RowType item);

    /**
     * Exchange the i-th row with the k-th row
     *
     * @param i The number of the row
     * @param k The number of the row
     */
    void exchangeRows(int i, int k);

    /**
     * Return the partition of the data set
     *
     * @return The partition
     */
    default IPartitionPolicy getPartitionPolicy(){return null;}

}
