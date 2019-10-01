package stats;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import utils.TableOperations;

/**
 * Simple class to perform linear regression
 */
public class LinearRegression {

    public LinearRegression(int numCoeffs){

        if(numCoeffs <= 1){
            throw new IllegalArgumentException("Cannot perform linear regression with "+numCoeffs+" need at least 2");
        }

        this.coeffs_ = new double[numCoeffs];
        this.zeroCoeffs();


    }


    /**
     * Fit a line using the given data set
     */
    public void fit(Table dataSet, String[] xCols, String yCol){

        // make sure the y column is in the dataSet

        // the object that will do the fitting for us
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.setNoIntercept(false); // always include the intercept

        DoubleColumn y = dataSet.doubleColumn(yCol);
        double[][] x = TableOperations.getTableColumnsForRegressionMatrix(dataSet, xCols, y.size());
        regression.newSampleData(y.asDoubleArray(), x);
        this.coeffs_ = regression.estimateRegressionParameters();
    }

    /**
     * Zero the coefficients
     */
    public void zeroCoeffs(){

        for(int c = 0; c<this.coeffs_.length; ++c){
            this.coeffs_[c] = 0.0;
        }
    }

    /**
     * The coefficients of the linear model
     */
    private double[] coeffs_;
}
