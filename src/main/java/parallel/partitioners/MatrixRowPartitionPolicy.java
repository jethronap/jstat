package parallel.partitioners;

import java.util.List;

public class MatrixRowPartitionPolicy implements IPartitionPolicy {

    /**
     * Constructor
     */
    public MatrixRowPartitionPolicy(List<List<Integer>> partitions){
        this.partitions = partitions;
    }

    /**
     * Set the partitions
     */
    public void setPartitions(List<List<Integer>> partitions){
        this.partitions = partitions;
    }

    /**
     * Returns the indices associted with the i-th partition
     * @param i
     * @return
     */
    public List<Integer> getParition(int i){
        return this.partitions.get(i);
    }


    /**
     * How many partitions this policy has
     */
    public int numPartitions(){
        return this.partitions.size();
    }


    /**
     * The partitions of the matrix rows
     */
    List<List<Integer>> partitions;
}
