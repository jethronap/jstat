package datastructs;

import parallel.partitioners.IPartitionPolicy;

public interface I2DDataSet<RowType> {

    /**
     * Copy from the given IDataSetWrapper
     */
    I2DDataSet copy();

    /**
     * Build a new instance of this class
     */
    I2DDataSet create(int m, int n);


    /**
     * How many rows the dataset has
     */
    int m();

    /**
     * How many columns the dataset has
     */
    int n();

    /**
     * Returns the i-th row of the dataset
     */
    RowType getRow(int i);


    /**
     * Returns the submatrix representation
     */
    <E> void getSubMatrix(E[][] elements, int numColsToInclude, int... includeCols);


    /**
     * Set the row-th row  of the dataset
     */
    void set(int row, RowType item);

    /**
     * Exchange the i-th row with the j-th row
     */
    void excahngeRows(int i, int k);

    /**
     * Return the i-th partition of the dataset
     */
    default IPartitionPolicy getPartitionPolicy(){return null;}

}
