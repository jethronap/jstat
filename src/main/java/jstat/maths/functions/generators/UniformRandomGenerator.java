package jstat.maths.functions.generators;

import jstat.datastructs.I2DDataSet;
import jstat.datastructs.IVector;

import java.util.*;

public class UniformRandomGenerator<T> implements IRandomGenerator<T> {

    @Override
    public <DataSetTp extends I2DDataSet<IVector<T>>> List<IVector<T>> generate(DataSetTp dataSet, int n){

        int min = 0;
        int max = dataSet.m();
        Random random = new Random();
        List<IVector<T>> result = new ArrayList<>();
        Set<Integer> touched = new HashSet<>();

        for(int r=0; r<n; ++r){

            int candidate = random.nextInt((max - min) + 1) + min;

            if(touched.contains(candidate)){
                continue;
            }
            result.add(dataSet.getRow(candidate));
            touched.add(candidate);
        }

        return result;
    }
}
