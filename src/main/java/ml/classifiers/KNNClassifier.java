package ml.classifiers;

import datastructs.IVector;
import maths.functions.distances.DistanceCalculator;
import ml.classifiers.utils.ClassificationVoter;
import datastructs.I2DDataSet;
import utils.Pair;

import java.util.*;


/**
 * KNNClassifier performs classification using the KNN algorithm
 */
public class KNNClassifier< DataType, DataSetType extends I2DDataSet<IVector<DataType>>,
                           DistanceType extends DistanceCalculator,
                           VoterType extends ClassificationVoter> {

    /**
     * Constructor
     */
    public KNNClassifier(int k, boolean copyDataset){
        this.k = k;
        this.copyDataset = copyDataset;
    }

    /**
     * How many neighbors the algorithm is using
     * @return
     */
    public int nNeighbors() {
        return this.k;
    }

    /**
     * Set the object that calculates the distance between instances in the dataset
     * @param distanceCalculator
     */
    public void setDistanceCalculator(DistanceType distanceCalculator){
        this.distanceCalculator = distanceCalculator;
    }

    /**
     * Set the object that calculates the class
     * @param voter
     */
    public void setMajorityVoter(VoterType voter){
        this.majorityVoter = voter;
    }

    /**
     * Train the model using the provided dataset
     */
    public void train(DataSetType dataSet, List<Integer> labels){

        if(this.copyDataset){
            this.dataSet = (DataSetType) dataSet.copy();

        }
        else{
            this.dataSet = dataSet;
            this.labels = labels;
        }
    }


    /**
     * Predict the class of the given data point
     */
    public <PointType> Integer  predict(PointType point){

        if(this.majorityVoter == null){
            throw new IllegalStateException(" Majority voter has not been set");
        }

        if(this.distanceCalculator == null){
            throw new IllegalStateException("Distance calculator has not been set");
        }

        // loop over the items in the dataset and compute distances
        for (int i = 0; i < this.dataSet.m(); i++) {
            this.majorityVoter.addItem(i, this.distanceCalculator.calculate(this.dataSet.getRow(i), point));
        }


        return this.getTopResult(); //maxEntry.getKey();
    }

    protected int getTopResult(){

        // get the top k results
        List<Pair<Integer, Double>> results = this.majorityVoter.getResult(this.k);
        this.majorityVoter.clear();

        Map<Integer, Integer> idxMap = new HashMap<>();

        for(int i=0; i<results.size(); ++i){
            int classIdx = (int) this.labels.get(results.get(i).first);

            if(idxMap.containsKey(classIdx)){
                idxMap.put(classIdx, idxMap.get(classIdx) + 1);
            }
            else{
                idxMap.put(classIdx, 1);
            }
        }

        Map.Entry<Integer, Integer> maxEntry = Collections.max(idxMap.entrySet(),
                (Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) -> e1.getValue()
                        .compareTo(e2.getValue()));

        return maxEntry.getKey();
    }


    /**
     * Number of neighbors to consider
     */
    protected int k;

    /**
     * flag indicating whether the dataset should be fully copied
     */
    protected boolean copyDataset;

    /**
     * The dataset
     */
    protected DataSetType dataSet;

    /**
     * The labels
     */
    protected List<Integer> labels;

    /**
     * The distance used
     */
    DistanceType distanceCalculator;

    /**
     * How to get the majority set
     */
    VoterType majorityVoter;
}
