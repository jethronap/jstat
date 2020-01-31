package maths;

import parallel.partitioners.IPartitionPolicy;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.*;

/**
 * Represents a dense matrix set.
 */
public class DenseMatrixSet<T> implements I2DDataSet<IVector<T>> {

    /**
     * Constructor
     */
    public DenseMatrixSet(RowType.Type rowType, RowBuilder rowBuilder){
        this.rowType = rowType;
        this.rowBuilder = rowBuilder;
    }

    /**
     * Constructor
     */
    public DenseMatrixSet(RowType.Type rowType, RowBuilder builder, int m, int n, T val){
        this(rowType, builder);
        this.createInternal(m ,n);
        for(int i=0; i<this.m(); ++i){
            for(int j=0; j<n(); ++j){
                this.set(i, j, val);
            }
        }
    }

    /**
     * Constructor
     */
    public DenseMatrixSet(RowType.Type rowType, RowBuilder builder, Table data){
        this(rowType, builder);
        this.initializeFrom(data);
    }

    /**
     * Copy constructors
     */
    public DenseMatrixSet(final DenseMatrixSet<T> other){
        this.initializeFrom(other);
    }

    /**
     * Copy this matrix
     */
    @Override
    public I2DDataSet<IVector<T>> copy(){

        return new DenseMatrixSet(this);
    }

    /**
     * Create a new matrix
     */
    @Override
    public I2DDataSet<IVector<T>> create(int m, int n){

        //DenseMatrix matrix = new DenseMatrix();
        this.createInternal(m, n);
        return this;
    }

    /**
     * How many rows the dataset has
     */
    @Override
    public final int m(){return this.data.size();}

    /**
     * How many columns the dataset has
     */
    @Override
    public final int n(){return this.data.get(0).size();}

    /**
     * Initialize the matrix from the given Table dataset
     */
    public void initializeFrom(final Table table){

        if(table == null){
            throw new IllegalArgumentException("Input Table should not be null");
        }

        // how many rows and columns
        int m = table.rowCount();
        int n = table.columnCount();

        this.createInternal(m, n);

        int rowCounter = 0;
        for (Row row: table ) {

            IVector<T> vecRow = this.rowBuilder.build(this.rowType, row.columnCount()); //).create(row.columnCount()); //new Vector(row.columnCount());
            vecRow.set(row);
            this.set(rowCounter++, vecRow);
        }
    }

    /**
     * Initialize the matrix from the given DenseMatrix
     */
    public void initializeFrom(final DenseMatrixSet<T> other){

        if(other == null){
            throw new IllegalArgumentException("Input DenseMatrix should not be null");
        }

        this.createInternal(other.m(), other.n());

        for (int rowIdx = 0; rowIdx < other.m(); rowIdx++) {


            IVector<T> vecRow = this.rowBuilder.build(this.rowType, other.n()); //).create(other.n());  //new Vector(other.n());
            vecRow.set(other.getRow(rowIdx));
            this.set(rowIdx, vecRow);
        }
    }

    /**
     * Given the number of columns to include and the column indices
     * create a submatrix that has all the rows and columns specified
     */
    @Override
    public final <E> void getSubMatrix(E[][] subMatix, int numColsToInclude, int... includeCols){

        //T[][] subMatix = new T[this.m()][numColsToInclude];

        for(int i=0; i<this.m(); ++i){

            int colCounter=0;
            for( int col:includeCols){
                subMatix[i][colCounter++] = (E) this.data.get(i).get(col);
            }
        }
    }

    /**
     * Given the number of columns to include and the column indices
     * create a submatrix that has all the rows and columns specified
     */
    public final void duplicateColumn(int column){

        IVector<T> col = getColumn(column);

        for(int i=0; i<this.m(); ++i){

            IVector<T> vec = this.data.get(i);
            vec.resize(vec.size() + 1);

            vec.set(vec.size()-1, col.get(i));
        }
    }

    /**
     * Set the (i,j) entry of the matrix
     */
    public final void set(int i, int j, T value){

        if( i >= m() || i < 0 ){
            throw new IllegalArgumentException("Invalid row index");
        }

        if( j >= n() || j < 0 ){
            throw new IllegalArgumentException("Invalid column index");
        }

        this.data.get(i).set(j, value);
    }

    /**
     * Set the i-th row
     */
    public final void set(int i, IVector<T> value){

        if( i >= m() || i < 0 ){
            throw new IllegalArgumentException("Invalid row index");
        }

        if(value.size() != this.n()){
            throw new IllegalArgumentException("Invalid number of columns");
        }

        this.data.get(i).set(value);
    }

    /**
     * Set the i-th row
     */
    public final void set(int i, Double... value){

       this.set(i, (IVector<T>) this.rowBuilder.build(this.rowType, this.m(), value)); //.create(this.m() , value)); //new Vector(value));
    }

    /**
     * Set the c-th column from the DoubleColumn data
     */
    public final void setColumn(int c, DoubleColumn col){

        if(col.size() != this.m()){
            throw new IllegalArgumentException("Column size not equal to the number of rows");
        }

        for(int i=0; i<this.m(); ++i){

            IVector<T> row =  this.data.get(i);
            for(int j=0; j<this.n(); ++j){
                if(j==c){
                    row.set(j, (T) col.get(i)); //.getDouble(i));
                }
            }
        }
    }

    /**
     * Set the c-th column from the List data
     */
    public final void setColumn(int c, List<Double> col){

        if(col.size() != this.m()){
            throw new IllegalArgumentException("Column size not equal to the number of rows");
        }

        for(int i=0; i<this.m(); ++i){

            IVector row =  this.data.get(i);
            for(int j=0; j<this.n(); ++j){
                if(j==c){
                    row.set(j, col.get(i));
                }
            }
        }
    }

    /**
     * Returns a copy of the values of the column-th column
     */
    public final IVector<T> getColumn(int column){

        if(column <0 || column >= this.n()){
            throw new IllegalArgumentException("Invalid column index: "+column+" should be in [0,"+this.n()+")");
        }

        IVector<T> columnVals = this.rowBuilder.build(this.rowType, this.m()); //.create(this.m() , 0.0); //new Vector(this.m(), 0.0);

        for(int i=0; i<this.m(); ++i){
            columnVals.set(i, this.data.get(i).get(column));
        }

        return columnVals;
    }

    public final IVector<T> getRow(int r){

        if( r >= m() || r < 0 ){
            throw new IllegalArgumentException("Invalid row index");
        }

        return this.data.get(r);
    }

    public final T getEntry(int i, int j){

        if( i >= m() || i < 0 ){
            throw new IllegalArgumentException("Invalid row index");
        }

        if( j >= n() || j < 0 ){
            throw new IllegalArgumentException("Invalid column index");
        }

        return this.data.get(i).get(j);
    }

    private final void createInternal(int m, int n){

        if(m <= 0 || n<= 0){
            throw new IllegalArgumentException("Cannot create a matrix with zero rows or columns");
        }

        this.data = new ArrayList<IVector<T>>(m);

        for(int i=0; i<m; ++i){

            IVector<T> row = this.rowBuilder.build(this.rowType, n); //.create(n ,val); //new Vector(n, val);
            this.data.add(row);
        }
    }

    /**
     * Exchange the i-th row with the j-th row
     */
    @Override
    public void excahngeRows(int i, int k){

        if( (i>=this.m() || k>=this.m()) || (i < 0 || k < 0)){
            throw new IllegalArgumentException("Invalid row index given");
        }

        // exchange
        IVector<T> tmp = this.data.get(i);
        IVector<T> next = this.data.get(k);
        this.data.set(i, next);
        this.data.set(k, tmp);
    }

    /**
     * Set the partition policy for this matrix
     */
    public void setPartitionPolicy(IPartitionPolicy policy){
        this.partitionePolicy = policy;
    }

    /**
     * Returns the partiton policy
     */
    @Override
    public IPartitionPolicy getPartitionPolicy(){
        return this.partitionePolicy;
    }

    RowType.Type rowType;
    RowBuilder rowBuilder;
    private ArrayList<IVector<T>> data = null;
    IPartitionPolicy partitionePolicy = null;

}
