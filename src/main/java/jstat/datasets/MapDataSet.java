package jstat.datasets;

import jstat.datastructs.I2DDataSet;

import java.util.HashMap;
import java.util.Map;

public class MapDataSet<KeyType, RowType> implements I2DDataSet<RowType> {

    /**
     * Constructor
     */
    public MapDataSet(){
        this.data = new HashMap<>();
    }

    /**
     * How many rows the data set has
     *
     * @return The rows
     */
    @Override
    public int m(){
        return data.size();
    }

    /**
     * How many columns the data set has
     *
     * @return The columns
     */
    @Override
    public int n(){
        return 0;
    }

    /**
     * Returns the i-th row of the data set
     *
     * @param i The number of the row
     * @return The i-th row
     */
    @Override
    public RowType getRow(int i){

        KeyType key = this.findRow(i);

        if(key == null){
            throw new IllegalArgumentException("Row " + i + " does not exist");
        }
        return data.get(key);
    }


    /**
     * Returns the submatrix representation
     *
     * @param <E> A 2D array type
     * @param elements A 2D array
     * @param numColsToInclude Number of columns to include
     * @param includeCols Number of included columns
     */
    @Override
    public <E> void getSubMatrix(E[][] elements, int numColsToInclude, int... includeCols){
        throw new IllegalCallerException("This function should not be called");
    }

    /**
     * Set the row-th row  of the data set
     *
     * @param row Te number of the row
     * @param item The row type
     */
    @Override
    public void set(int row, RowType item){
        KeyType key = this.findRow(row);

        if(key == null){
            throw new IllegalArgumentException("Row " + row + " does not exist");
        }

        this.data.replace(key, item);
    }

    public void add(KeyType key, RowType row){
        this.data.put(key, row);
    }

    /**
     * Exchange the i-th row with the k-th row
     *
     * @param i The number of the row
     * @param k The number of the row
     */
    @Override
    public void exchangeRows(int i, int k){}

    /**
     * Find the assumed i-th row
     * @param i
     * @return The name of the i-th row
     */
    protected KeyType findRow(int i ){

        int counter = 0;

        for(Map.Entry<KeyType, RowType> entry: data.entrySet()){
            if(counter++ == i){
                return entry.getKey();
            }
        }

        return null;
    }

    private Map<KeyType, RowType> data;
}
