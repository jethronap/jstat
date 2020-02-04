package ml.classifiers;

import optimization.ISupervisedOptimizer;
import maths.I2DDataSet;
import maths.IVector;
import maths.Vector;
import maths.functions.IVectorRealFunction;

import java.util.List;

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
    public <OutputType> OutputType train(final DataSetType dataSet, final Vector labels){
        return this.optimizer.optimize(dataSet, labels, this.hypothesis);
    }

    /**
     * Predict the class of the given data point
     */
    @Override
    public <PointType> Integer  predict(PointType point){
        Vector vec = (Vector) point;
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
