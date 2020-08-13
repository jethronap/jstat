package jstat.datastructs;


import jstat.datasets.VectorDouble;

public class DoubleVectorBuilder implements IRowBuilder<VectorDouble> {


    @Override
    public VectorDouble create(){
            return new VectorDouble();
    }

    /**
     * Create a row of type RowType
     */
    @Override
    public VectorDouble create(int n){
        return new VectorDouble(n, 0.0);
    }

    /**
     * Create a row of type RowType
     */
    @Override
    public <T> VectorDouble create(T... vals){
        return new VectorDouble((Double[])(vals));
    }


}
