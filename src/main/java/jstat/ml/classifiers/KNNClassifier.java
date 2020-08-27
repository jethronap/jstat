package jstat.ml.classifiers;

import jstat.maths.functions.distances.IDistanceCalculator;
import jstat.ml.classifiers.utils.ClassificationVoter;
import jstat.utils.Pair;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.*;


/**
 * KNNClassifier performs classification using the KNN algorithm
 */
public class KNNClassifier<DistanceType extends IDistanceCalculator,
                           VoterType extends ClassificationVoter> {

    /**
     * Constructor
     *
     * @param k The cluster numbers
     * @param copyDataset Boolean for detecting the copied data set
     */
    public KNNClassifier(int k, boolean copyDataset){
        this.k = k;
        this.copyDataset = copyDataset;
    }

    /**
     * How many neighbors the algorithm is using
     * @return The number of neighbours
     */
    public int nNeighbors() {
        return this.k;
    }

    /**
     * Set the object that calculates the distance between instances in the dataset
     * @param distanceCalculator The chosen distance calculator
     */
    public void setDistanceCalculator(DistanceType distanceCalculator){
        this.distanceCalculator = distanceCalculator;
    }

    /**
     * Set the object that calculates the class
     * @param voter Class calculator
     */
    public void setMajorityVoter(VoterType voter){
        this.majorityVoter = voter;
    }

    /**
     * Train the model using the provided data set
     *
     * @param dataSet The given data set
     * @param labels The given labels
     */
    public void train(INDArray dataSet, List<Integer> labels){
            this.dataSet =  dataSet;
    }


    /**
     * Predict the class of the given data point
     *
     * @param <PointType> A generic point type
     * @param point The given point
     * @return A point
     */
    public Integer  predictPoint(INDArray point){

        if(this.majorityVoter == null){
            throw new IllegalStateException(" Majority voter has not been set");
        }

        if(this.distanceCalculator == null){
            throw new IllegalStateException("Distance calculator has not been set");
        }

        // loop over the items in the data set and compute distances
        for (int i = 0; i < this.dataSet.size(0); i++) {
            this.majorityVoter.addItem(i, this.distanceCalculator.calculate(this.dataSet.getRow(i), point));
        }

        return this.getTopResult();
    }

    public List<Integer> predict(INDArray dataSet){

        List<Integer> predictions = new ArrayList<>((int)dataSet.size(0));

        // invalidate the predictions
        for(int i=0; i<dataSet.size(0); ++i){
            predictions.add(-1);
        }

        // loop over all points in the dataset and make a prediction
        for(int r=0; r<dataSet.size(0); ++r){
            predictions.set(r, this.predictPoint(dataSet.getRow(r)));
        }

        return predictions;
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
    protected INDArray dataSet;

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
