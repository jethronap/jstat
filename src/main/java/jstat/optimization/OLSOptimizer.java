package jstat.optimization;

import jstat.utils.IterativeAlgorithmResult;
import jstat.maths.functions.IVectorRealFunction;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Ordinary Least Squares optimizer for a real vector function
 */
public class OLSOptimizer implements ISupervisedOptimizer {

    /**
     * Optimize f on the given data
     */
    public <OutPutType> OutPutType optimize(final INDArray data, final INDArray y, IVectorRealFunction f){

        IterativeAlgorithmResult reslt = new IterativeAlgorithmResult();
        reslt.numThreadsUsed = 1;

        // the object that will do the fitting for us
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();

        int numColsToInclude = 2;

        double[][] x = data.toDoubleMatrix();
        double[] yArray = y.toDoubleVector();

        regression.newSampleData(yArray, x);
        double[] coeffs = regression.estimateRegressionParameters();
        f.setCoeffs(coeffs);
        return (OutPutType) reslt;
    }

}
