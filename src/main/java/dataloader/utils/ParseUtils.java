package dataloader.utils;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {

    public static List< Double > parseAsDouble(List<Object> dataSet){

        List<Double> data = new ArrayList<Double>( dataSet.size());
        for(int i=0; i< dataSet.size(); ++i){
            data.add( Double.parseDouble((String) dataSet.get(i)));
        }
        return data;
    }
}
