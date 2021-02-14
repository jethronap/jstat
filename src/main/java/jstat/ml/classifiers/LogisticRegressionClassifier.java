package jstat.ml.classifiers;


import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Logistic regression classification
 */
public class LogisticRegressionClassifier extends ClassifierBase {



    /**
     * Constructor.
     *
     * @param hypothesis A hypothesis type
     */
    public LogisticRegressionClassifier(IVectorRealFunction hypothesis){
        super();
        this.hypothesis = hypothesis;
    }


    /**
     * Set the model paramters
     * @param parameters
     */
    @Override
    public void setParameters(INDArray parameters){

    }

    /**
     * Returns the model parameters
     * @return INDArray
     */
    @Override
    public INDArray getParameters(){
        return null;
    }

    /**
     * Predict the class of the given data point
     */
    /*@Override
    public Integer  predict(INDArray point){
        INDArray vec = point;
        double hypothesisVal = this.hypothesis.evaluate(vec);

        if(hypothesisVal >= 0.5){
            return 1;
        }
        return 0;
    }*/

    /**
     * Predict the outputs over the given dataset
     */
    @Override
    public INDArray predict(INDArray dataSet){

        INDArray predictions = Nd4j.zeros(dataSet.size(0));

        for(int idx=0; idx<dataSet.size(0); ++idx){
            predictions.putScalar(idx, this.hypothesis.evaluate(dataSet.getRow(idx)));
        }

        return predictions;
    }

    /**
     * The hypothesis function assumed by the regressor
     */
    protected IVectorRealFunction hypothesis;
}
