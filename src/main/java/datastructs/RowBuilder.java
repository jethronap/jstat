package datastructs;


import datastructs.DoubleVectorBuilder;
import datastructs.IRowBuilder;
import datastructs.IntegerVectorBuilder;
import datastructs.RowType;

import java.util.HashMap;
import java.util.Map;

public class RowBuilder {


    private static Map<RowType.Type, IRowBuilder<?>> elementBuilder = new HashMap<>();

    static
    {
        elementBuilder.put(RowType.Type.DOUBLE_VECTOR, new DoubleVectorBuilder());
        elementBuilder.put(RowType.Type.INTEGER_VECTOR, new IntegerVectorBuilder());
    }

    public <Row> Row build(RowType.Type type){

        return (Row) elementBuilder.get(type).create();
    }

    public <Row> Row build(RowType.Type type, int n){

        return (Row) elementBuilder.get(type).create(n);
    }

    public <Row, T> Row build(RowType.Type type, T... vals){

        return (Row) elementBuilder.get(type).create(vals);
    }

}
