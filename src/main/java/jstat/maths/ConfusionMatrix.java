package jstat.maths;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.List;



/**
 * ConfusionMatrix
 */
public class ConfusionMatrix {

    /**
     * Default constructor
     */
    public ConfusionMatrix(){

    }

    /**
     * Constructor
     */
    public ConfusionMatrix(List<Integer> actual, List<Integer> predicted, int nClasses){
        this.buildFrom(actual, predicted, nClasses);
    }

    /**
     * Given the actual and the predicted classes and the total number
     * of different classes construct the confusion matrix
     * @param actual
     * @param predicted
     * @param nClasses
     */
    void buildFrom(List<Integer> actual, List<Integer> predicted, int nClasses){

        if(nClasses<2){
            throw new IllegalArgumentException("Number of classes should be at least 2. Got "+nClasses);
        }

        if(actual.size() == 0){
            throw new IllegalArgumentException("Cannot construct ConfusionMatrix from empty actual data");
        }

        if(actual.size() != predicted.size()){
            throw new IllegalArgumentException("A ConfusionMatrix is a square matrix but the given data are: "+
                     actual.size()+
                    " and columns: "+
                    predicted.size());
        }

        this.data = Nd4j.zeros(nClasses, nClasses);

        // construct the matrix row-wise
        for(int r=0; r<actual.size(); ++r){

            int row_idx = actual.get(r);

            if(row_idx >= nClasses){
                throw new IllegalArgumentException("Invalid class index. Index not in [0, " + nClasses +")");
            }

            if(row_idx == predicted.get(r)){
                this.data.putScalar(row_idx ,row_idx, 1);
            }
            else{
                this.data.putScalar(row_idx , predicted.get(r), 1);
            }
        }

        // set the total count of observations
        this.totalCount = actual.size();
    }

    /**
     * Returns the accuracy of the classifier
     */
    public double accuracy(){
        return (double)truePositives()/(double)totalCount();
    }

    /**
     * Returns the misclassification rate
     */
    public double misclassificationRate(){
        return 1.0 - accuracy();
    }

    /**
     * Recall also known as sensitivity or true positive rate for class c
     */
    public double recallClass(int c){

        // how many times did we predicted the class
        int classCounts = getClassCounts(c);

        // how many times we missed it overall
        int classIncorrectCounts = this.getClassIncorrectCounts(c);

        return (double) classCounts/ (double) (classIncorrectCounts + classCounts);
    }

    /**
     * Return the true positives. This is simply the sum of the diagonal elements
     */
    public int truePositives(){
        int result = 0;
        for(int r=0; r < this.data.size(0); ++r){

            result += this.getClassCounts(r);
        }
        return result;
    }

    /**
     * Get the class counts
     * @param c
     * @return
     */
    public int getClassCounts(int c){

        if( c >= this.data.size(0) || c < 0){
            throw new IllegalArgumentException("Invalid class index. Index "+
                    c+
                    " not in [0, "+
                    this.data.size(1)+
                    ")");
        }

        return this.data.getInt(c,c);
    }

    /**
     * Get class incorrect counts
     * @param c
     * @return
     */
    public int getClassIncorrectCounts(int c){
        if( c >= this.data.size(0) || c < 0){
            throw new IllegalArgumentException("Invalid class index. Index "+
                    c+
                    " not in [0, "+
                    this.data.size(1)+
                    ")");
        }

        int result = 0;

        for(int r = 0; r<this.data.size(0); ++r){
            if( r != c){
                result +=  this.data.getInt(c , r);
            }
        }

        return result;
    }

    /**
     * Returns the counts that class c was predicted as other class
     *
     * @param c
     * @param other
     * @return
     */
    public int getClassCountsAsOtherClass(int c, int other){
        if( c >= this.data.size(0)){
            throw new IllegalArgumentException("Invalid class index. Index "+
                    c+
                    " not in [0, "+
                    this.data.size(0)+
                    ")");
        }

        if( other >= this.data.size(0)){
            throw new IllegalArgumentException("Invalid class index. Index "+
                    other+
                    " not in [0, "+
                    this.data.size(1)+
                    ")");
        }

        return this.data.getInt(c, other);
    }

    /**
     *  Returns the total number of observations
     */
    public int totalCount(){return totalCount;}


    private INDArray data;
    private int totalCount;

}
