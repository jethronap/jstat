package optimization;

import utils.IterativeAlgorithmResult;
import datastructs.I2DDataSet;
import datastructs.IVector;
import datasets.VectorDouble;
import maths.functions.IVectorRealFunction;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import utils.ArrayUtils;
import utils.ListUtils;

/**
 * Ordinary Least Squares optimizer for a real vector function
 */
public class OLSOptimizer implements ISupervisedOptimizer {

    /**
     * Optimize f on the given data
     */
    public <OutPutType, DataSetType extends I2DDataSet<IVector<Double>>> OutPutType optimize(final DataSetType data, final VectorDouble y, IVectorRealFunction f){

        IterativeAlgorithmResult reslt = new IterativeAlgorithmResult();
        reslt.numThreadsUsed = 1;

        // the object that will do the fitting for us
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();

        int numColsToInclude = 2;
        Double[][] x = new Double[data.m()][numColsToInclude];
        data.getSubMatrix(x, numColsToInclude, 1, 2);

        double[] yArray = ListUtils.toDoubleArray(y.getRawData());
        regression.newSampleData(yArray, ArrayUtils.toArray(x));
        double[] coeffs = regression.estimateRegressionParameters();
        f.setCoeffs(coeffs);
        return (OutPutType) reslt;
    }

    /**
     * Optimize approximate function f on the given dataset and the
     * given labels. Derived classes specify the output
     */
    /*public <OutPutType, DataSetType extends I2DDataSet> OutPutType optimize(final DataSetType data, final List<Integer> y, IVectorRealFunction f){
        throw new NotImplementedException("This function has not been implemented");
    }*/
}
