package jstat.datastructs;

public interface IRowBuilder<RowType> {

    /**
     * Create a row of type RowType
     */
    RowType create();

    /**
     * Create a row of type RowType
     */
    RowType create(int n);

    /**
     * Create a row of type RowType
     */
    <T> RowType create(T... vals);
}
