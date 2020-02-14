package ml.classifiers;

import datasets.VectorDouble;
import optimization.ISupervisedOptimizer;
import datastructs.I2DDataSet;
import datastructs.IVector;
import maths.functions.IVectorRealFunction;

public class LogisticRegressionClassifier<DataSetType extends I2DDataSet<IVector<Double>>,
                                HypothesisType extends IVectorRealFunction<IVector<Double>>> extends ClassifierBase<DataSetType> {

    /**
     * Constructor.
     */
    public LogisticRegressionClassifier(HypothesisType hypothesis, ISupervisedOptimizer optimizer){
        super();
        this.hypothesis = hypothesis;
        this.optimizer = optimizer;
    }

    /**
     * Train the model using the provided dataset
     */
    @Override
    public <OutputType> OutputType train(final DataSetType dataSet, final VectorDouble labels){
        return this.optimizer.optimize(dataSet, labels, this.hypothesis);
    }

    /**
     * Predict the class of the given data point
     */
    @Override
    public <PointType> Integer  predict(PointType point){
        VectorDouble vec = (VectorDouble) point;
        double hypothesisVal = this.hypothesis.evaluate(vec);

        if(hypothesisVal >= 0.5){
            return 1;
        }
        return 0;
    }

    /**
     * The hypothesis function assumed by the regressor
     */
    protected HypothesisType hypothesis;
    protected ISupervisedOptimizer optimizer;
}
