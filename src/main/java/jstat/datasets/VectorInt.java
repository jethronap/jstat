package jstat.datasets;

import jstat.datastructs.IVector;
import jstat.datastructs.RowType;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.List;

public class VectorInt implements IVector<Integer> {

    /**
     * Creates an vector with initial capacity of 10
     */
    public VectorInt(){

        this.data = new VectorStorage<>(10, 0);
    }


    /**
     * Creates a vector of given size with entries initialized to val
     */
    public VectorInt(int size, int val){

        this.data = new VectorStorage<>(size, val);
    }


    /**
     * Create a vector from the given double values
     */
    public VectorInt(Integer... data){
        this.data = new VectorStorage<>(data);

    }

    /**
     * Creates a vector of given size with entries initialized to 0.0
     */
    public VectorInt(int size){
        this(size, 0);
    }


    /**
     * Create a vector from another vector i.e. copy constructor
     */
    public VectorInt(IVector<Integer> data){
        this(data.size(), 0);
        this.set(data);
    }

    /**
     * Create from the given Table and the given column name
     */
    public VectorInt(Table table, String columnName){
        this(table.doubleColumn(columnName));
    }

    /**
     * Create a vector from the given DoubleColumn
     */
    public VectorInt(DoubleColumn column){
        this.data = new VectorStorage<>(column.size(), 0);
        this.set(column);
    }

    @Override
    public final RowType.Type getType(){return RowType.Type.INTEGER_VECTOR; }

    /**
     * Returns true if the vector is empty
     */
    @Override
    public boolean empty(){
        return this.data.isEmpty();
    }

    /**
     * Build a new instance of this class
     */
    @Override
    public IVector<Integer> create(int size){
        return new VectorInt(size, 0);
    }

    /**
     * Build a new instance of this class
     */
    @Override
    public IVector<Integer> create( Integer... value){
        return new VectorInt(value);
    }

    @Override
    public IVector<Integer> create(){return new VectorInt();}

    /**
     * Resize the vector
     */
    @Override
    public final void resize(int size){

        if(data == null){
            this.data.create(size, 0);
        }
        else{

            // nothing to do here if sizes are the same
            if(size == data.size()){
                return;
            }

            VectorStorage<Integer> newVec = new VectorStorage<Integer>(size, 0);

            if(size > data.size()){

                for(int i=0; i<data.size(); ++i){

                    newVec.set(i, data.get(i));
                }
            }
            else{

                for(int i=0; i<size; ++i){

                    newVec.set(i, data.get(i));
                }
            }

            this.data = newVec;
        }
    }

    /**
     * Exchange the i-th entry with the j-th
     */
    @Override
    public void excahnge(int i, int k){
        this.data.exchange(i, k);
    }


    /**
     * Returns the size of the vector
     */
    @Override
    public final int size(){

        return this.data.size();
    }


    /**
     * Zero all the entries in the dataset
     */
    public final void zero(){

        if(this.data == null){

            throw new NullPointerException("Vector has not been created");
        }

        if(this.data.size() == 0){

            throw new IllegalStateException("Vector has not been initialized properly");
        }

        for(int i=0; i<this.data.size(); ++i){

            this.data.set(i, 0);
        }
    }

    /**
     * Returns the i-th entry of the Vector
     */
    @Override
    public final Integer get(int i){
        return this.data.get(i);
    }

    /**
     * Set the i-th entry to val
     */
    @Override
    public final void set(int i, Integer val){
        this.data.set(i, val);
    }

    /**
     * Set the i-th entry to val
     */
    public final void set(int i, int val){
        this.data.set(i, val);
    }

    /**
     * Set the  entries to val
     */
    @Override
    public final void set(IVector<Integer> values){

        if(values.size() != this.size()){
            throw  new IllegalArgumentException("Invalid Vector size: "+ values.size() + " != " + this.size());
        }

        for(int i=0; i<this.size(); ++i){
            this.data.set(i, values.get(i));
        }
    }

    /**
     * Set the values of the Vector using the Row provided
     */
    public final void set(Row row){

        if(row == null){
            throw  new IllegalArgumentException("Row input should not be null");
        }

        for (int i = 0; i < row.columnCount(); i++) {
            this.set(i, row.getInt(i));
        }
    }

    /**
     * Set the data of the vector from a DoubleColumn
     */
    public final void set(DoubleColumn column){

        for (int i = 0; i < column.size() ; i++) {
            this.set(i, (int) column.getDouble(i));
        }
    }

    /**
     * Set the data from a simple array
     */
    public final void set(Integer[] data){
        this.data.set(data);
    }

    /**
     * Set the coefficients of the function
     */
    public void set(int[] data){

        for (int i = 0; i < data.length ; i++) {
            this.set(i, data[i]);
        }
    }

    @Override
    public Integer[] toArray(){

        Integer[] arrData = new Integer[this.data.size()];

        for(int i=0; i<this.data.size(); ++i){
            arrData[i] = this.data.get(i);
        }

        return arrData;
    }

    /**
     * Scale the components of the vector with the given scalar
     */
    public final void scale(int factor){

        if(this.data.size() == 0){

            throw new IllegalStateException("Vector has not been initialized properly");
        }

        for(int i=0; i<this.data.size(); ++i){

            this.data.set(i, factor*this.data.get(i));
        }
    }

    /**
     * operation +=
     */
    @Override
    public void add(int i, Integer value){
        int val = this.data.get(i);
        this.data.set(i , val + value);
    }

    /**
     * Normalze the Vector
     */
    public final void normalize(){

        /*double length = VectorOperations.l2Norm(this);

        if(length - CommonConstants.getTol() < 0.0){
            throw new IllegalStateException("Zero length vector cannot be normalized");
        }

        this.scale(1.0/length);*/

    }

    /**
     * Returns true if the given value is contained in the vector
     */
    public boolean contains(Integer val){
        return this.data.contains(val);
    }

    /**
     * get the raw data
     */
    @Override
    public List<Integer> getRawData(){
        return data.getRawData();
    }

    /**
     * The vector data
     */
    VectorStorage<Integer> data = null;
}
