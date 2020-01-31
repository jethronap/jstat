package parallel.partitioners;

import java.util.List;

public interface IPartitionPolicy {

    /**
     * Returns the indices associted with the i-th partition
     * @param i
     * @return
     */
    List<Integer> getParition(int i);


    /**
     * How many partitions this policy has
     */
    int numPartitions();


}
