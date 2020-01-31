package parallel.partitioners;

import java.util.ArrayList;
import java.util.List;

public class RangePartitioner {


    public static  List<List<Integer>> partition(int start, int end, int nParts){

        List<List<Integer>> partitions = new ArrayList<>();

        if(nParts ==0 ){

            partitions.add(new ArrayList<>());

            for (int i = start; i < end; i++) {
                partitions.get(0).add(i);
            }

            return partitions;
        }

        int workSize = (end-start)/nParts;
        int partStart = 0;
        int partEnd = workSize;
        for (int i = 0; i < nParts; i++) {

            partitions.add(new ArrayList<>());

            for (int p = partStart; p < partEnd; ++p) {
                partitions.get(i).add(p);
            }

            partStart = partEnd;
            partEnd += workSize;
        }

        // collect what is left

        while(partStart < end){
            partitions.get(nParts - 1).add(partStart++);
        }

        return partitions;
    }
}
