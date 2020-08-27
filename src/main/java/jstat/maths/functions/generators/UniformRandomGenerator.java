package jstat.maths.functions.generators;

import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.*;

public class UniformRandomGenerator implements IRandomGenerator {

    @Override
    public List<INDArray> generate(INDArray dataSet, int n){

        int min = 0;
        int max = (int) dataSet.size(0);
        Random random = new Random();
        List<INDArray> result = new ArrayList<>();
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
