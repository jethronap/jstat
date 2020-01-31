package maths;


import java.util.HashMap;
import java.util.Map;

public class RowBuilder {


    private static Map<RowType.Type, IRowBuilder<?>> elementBuilder = new HashMap<>();

    static
    {
        elementBuilder.put(RowType.Type.DOUBLE_VECTOR, new DoubleVectorBuilder());
        elementBuilder.put(RowType.Type.INTEGER_VECTOR, new IntegerVectorBuilder());
    }

    <Row> Row build(RowType.Type type){

        return (Row) elementBuilder.get(type).create();
    }

    <Row> Row build(RowType.Type type, int n){

        return (Row) elementBuilder.get(type).create(n);
    }

    <Row, T> Row build(RowType.Type type, T... vals){

        return (Row) elementBuilder.get(type).create(vals);
    }

}
