package datastructs;

import tech.tablesaw.api.Row;
import java.util.List;

/**
 * General interface for vector-like data structures
 */
public interface IVector<E>{


    /**
     * Build a new instance of this class
     *
     * @param size The size
     * @return The new instance
     */
    IVector<E> create(int size);


    /**
     * Build a new instance of this class
     *
     * @param value The values
     * @return The new instance
     */
    IVector<E> create( E... value);

    /**
     * Create a default vector
     *
     * @return A new vector
     */
    IVector<E> create();


    /**
     * Resize the vector
     *
     * @param size The size
     */
    void resize(int size);


    /**
     * Set the data from the other IVector
     *
     * @param other The vector
     */
    void set(IVector<E> other);


    /**
     * Set the data from TableSaw Row
     *
     * @param row The given row
     */
    void set(Row row);

    /**
     * Set the i-th entry
     *
     * @param i The number of the entry
     * @param data The data
     */
    void set(int i, E data);

    /**
     * Add the data to the i-th entry
     */
    void add(int i, E data);

    /**
     * Returns the i-th element
     */
    E get(int i);

    /**
     * Exchange the i-th entry with the j-th
     */
    void excahnge(int i, int k);


    /**
     * Get the elements of the vector as an array
     * @return
     */
    E[] toArray();


    /**
     * get the raw data
     */
    List<E> getRawData();


    /**
     * Returns the type of the vector
     */
    RowType.Type getType();

    /**
     * Returns true if the ADT is empty
     */
    boolean empty();


    /**
     * Returns how many elements the ADT has
     */
    int size();

    /**
     * Returns true if the ADT contains the given element
     */
    default boolean contains(E data){return false;}

}
