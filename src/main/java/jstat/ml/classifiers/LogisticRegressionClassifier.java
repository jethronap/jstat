package jstat.ml.classifiers;

import jstat.optimization.ISupervisedOptimizer;
import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;

public class LogisticRegressionClassifier extends ClassifierBase {

    /**
     * Constructor.
     *
     * @param hypothesis A hypothesis type
     * @param optimizer An optimizer
     */
    public LogisticRegressionClassifier(IVectorRealFunction hypothesis, ISupervisedOptimizer optimizer){
        super();
        this.hypothesis = hypothesis;
        this.optimizer = optimizer;
    }

    /**
     * Train the model using the provided dataset
     */
    @Override
    public <OutputType> OutputType train(final INDArray dataSet, final INDArray labels){
        return this.optimizer.optimize(dataSet, labels, this.hypothesis);
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
    protected ISupervisedOptimizer optimizer;
}
