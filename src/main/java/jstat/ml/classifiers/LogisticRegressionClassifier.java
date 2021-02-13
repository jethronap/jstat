package jstat.ml.classifiers;


import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;

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
    @Override
    public Integer  predict(INDArray point){
        INDArray vec = point;
        double hypothesisVal = this.hypothesis.evaluate(vec);

        if(hypothesisVal >= 0.5){
            return 1;
        }
        return 0;
    }

    /**
     * The hypothesis function assumed by the regressor
     */
    protected IVectorRealFunction hypothesis;
}
