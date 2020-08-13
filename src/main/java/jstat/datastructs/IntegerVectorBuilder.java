package jstat.datastructs;


import jstat.datasets.VectorInt;

public class IntegerVectorBuilder implements IRowBuilder<VectorInt> {


    @Override
    public VectorInt create(){
        return new VectorInt();
    }

    /**
     * Create a row of type RowType
     */
    @Override
    public VectorInt create(int n){
        return new VectorInt(n, 0);
    }

    /**
     * Create a row of type VectorInt from the given list
     */
    @Override
    public <T> VectorInt create(T... vals){
        return new VectorInt((Integer[])(vals));
    }
}
