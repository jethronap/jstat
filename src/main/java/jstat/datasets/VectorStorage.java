package jstat.datasets;

import java.util.ArrayList;
import java.util.List;

public class VectorStorage<T> {


    /**
     * Creates a vector of given size with entries initialized to val
     */
    public VectorStorage(int size, T val){
        create(size, val);
    }


    /**
     * Create a vector from the given double values
     */
    public VectorStorage(T... data){
        this.data = new ArrayList<>();

        for(T val: data){
            this.data.add(val);
        }
    }

    /**
     * Create a vector from the given double values
     */
    public VectorStorage(List<T> data){
        this.data = new ArrayList<>();

        for(T val: data){
            this.data.add(val);
        }
    }


    /**
     * Exchange the i-th entry with the j-th
     */
    public void exchange(int i, int k){

        if( (i>=this.size() || k>=this.size()) || (i < 0 || k < 0)){
            throw new IllegalArgumentException("Invalid entry index given");
        }

        T tmp = this.data.get(i);
        this.data.set(i, this.data.get(k));
        this.data.set(k, tmp);
    }


    /**
     * Returns the size of the VectorStorage
     */
    public final int size(){

        if(data == null){
            return 0;
        }

        return this.data.size();
    }


    /**
     * Returns the i-th entry of the VectorStorage
     */

    public final T get(int i){
        return this.data.get(i);
    }

    /**
     * Set the i-th entry to val
     */
    public final void set(int i, T val){

        if(i <0 || i>= data.size()){
            throw  new IllegalArgumentException("Invalid index. index given not in [0, "+data.size()+")");
        }

        this.data.set(i, val);
    }


    /**
     * Set the elements of the vector
     */
    public void set(T[] data){
        for (int i = 0; i < data.length ; i++) {
            this.set(i, data[i]);
        }
    }

    /**
     * Create a VectorStorage with the given size
     * and set all items equal to val
     * @param size
     * @param val
     */
    public final void create(int size, T val){

        if(size == 0){
            throw new IllegalArgumentException("Cannot create a vector with zero size");
        }

        this.data = new ArrayList<T>(size);

        for(int i=0; i<size; ++i){

            data.add(val);
        }
    }

    /**
     * get the raw data
     */
    public List<T> getRawData(){
        return data;
    }


    /**
     * Returns true if the given value is contained in the vector
     */
    public boolean contains(T val){
        return this.data.contains(val);
    }


    /**
     * Returns true is the underlying data is empty
     */
    public final boolean isEmpty(){return this.data.isEmpty();}

    /**
     * The vector data
     */
    private ArrayList<T> data = null;

}
