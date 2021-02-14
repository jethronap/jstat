package jstat.ml.classifiers;

import jstat.maths.functions.distances.IDistanceMetric;
import jstat.parallel.partitioners.IPartitionPolicy;
import jstat.parallel.tasks.TaskBase;

import jstat.utils.Pair;
import jstat.utils.PairBuilder;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.*;
import java.util.concurrent.*;

public class ThreadedKNNClassifier extends KNNClassifier{

    /**
     * Constructor
     *
     * @param k The number of iterations
     * @param copyDataset The copied data set
     * @param executorService The service executed
     */
    public ThreadedKNNClassifier(int k, boolean copyDataset, ExecutorService executorService){
        super(k, copyDataset);
        this.executorService = executorService;
    }

    /**
     * Predict the class of the given data point. This class blocks until all
     * computations are completed
     */

    @Override
    public Integer  predictPoint(INDArray point){

        if(this.majorityVoter == null){
            throw new IllegalStateException(" Majority voter has not been set");
        }

        if(this.distanceCalculator == null){
            throw new IllegalStateException("Distance calculator has not been set");
        }

        /*if(this.dataSet.getPartitionPolicy().numPartitions() == 0){
            throw new IllegalStateException("Dataset does not have partitions set");
        }

        if(this.tasks == null){
            // generate tasks as many as the partitions of the dataset
            this.tasks = new ArrayList<>(this.dataSet.getPartitionPolicy().numPartitions());

        }

        CountDownLatch countDownLatch = new CountDownLatch(this.dataSet.getPartitionPolicy().numPartitions());

        // let's create the tasks and add them to the List
        for (int i = 0; i < this.dataSet.getPartitionPolicy().numPartitions(); i++) {

            this.tasks.add(new KNNTask<PointType>(i, point, this.dataSet, this.distanceCalculator, countDownLatch));
            this.executorService.submit((Callable<List<Pair<Integer, Object>>>)this.tasks.get(i));
        }*/

        // the main thread waits here
        //try {
        //    countDownLatch.await();
        //}
        //catch(InterruptedException e){
        //}

        // all tasks have finished let's collect the distances
        for (int t = 0; t < this.tasks.size(); t++) {

            List<Pair<Integer, Object>> taskResult = ((KNNTask)this.tasks.get(t)).getResult();
            this.majorityVoter.addItems(taskResult);
        }

        return this.getTopResult();
    }

    /**
     * The object responsible for executing the KNN
     */
    private ExecutorService executorService;

    /**
     * Private list that holds the tasks to submit
     */
    private List<KNNTask> tasks;

    /**
     * The class that represents the task to submit to the executor
     *
     */
    private class KNNTask extends TaskBase<List<Pair<Integer, Object>>>
    {

        /**
         * Constructor
         */
        public KNNTask(int taskId, INDArray point, INDArray dataSet, IDistanceMetric distanceCalculator,   CountDownLatch countDownLatch){

            this.setTaskId(taskId);
            this.setResult(new ArrayList<Pair<Integer, Object>>());
            this.point = point;
            this.dataSet = dataSet;
            this.distanceCalculator = distanceCalculator;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run(){

            // loop over the items in the dataset and compute distances
            // we don't want to loop over all rows but only to the
            // rows attached to the task. This is implicitly known
            // by the partitioning of the data set
            IPartitionPolicy partitionePolicy = null; //this.dataSet.getPartitionPolicy();
            List<Integer> rows = partitionePolicy.getParition(this.getTaskId());
            List<Pair<Integer, Object>> result = this.getResult();

            for (int i = 0; i < rows.size(); i++) {

                result.add(PairBuilder.makePair(rows.get(i), this.distanceCalculator.calculate(this.dataSet.getRow(rows.get(i)), point)));
            }

            this.setFinished( true );
            this.countDownLatch.countDown();
        }


        /**
         * The point type the task is working on
         */
        INDArray point;

        /**
         * The dataset the task is working on
         */
        private INDArray dataSet;

        /**
         * The distance used
         */
        private IDistanceMetric distanceCalculator;

        /**
         * Each task counts down this latch when finished
         */
        CountDownLatch countDownLatch;
    }

}
