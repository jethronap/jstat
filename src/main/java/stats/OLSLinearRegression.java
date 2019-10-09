package stats;

import org.apache.commons.math3.stat.regression.*;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import utils.TableOperations;

/**
 * Simple class to perform linear regression
 */
public class OLSLinearRegression {


    /**
     * Constructor
     */
    public OLSLinearRegression(){

    }


    /**
     * Returns the interception term
     * @return
     */
    public final double getIntercept(){

        return this.coeffs[0];
    }


    /**
     * Returns the coefficients of the regression
     */
    public final double[] getCoeffs(){
        return this.coeffs;
    }


    /**
     * Fit a line using OLS and the given data set
     */
    public void fit(Table dataSet, String[] xCols, String yCol){


        DoubleColumn yColData = dataSet.doubleColumn(yCol);

        if( yColData == null){
            throw new IllegalStateException("Column: "+yCol+" not in data set");
        }

        // the object that will do the fitting for us
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();

        DoubleColumn y = dataSet.doubleColumn(yCol);
        double[][] x = TableOperations.getTableColumnsForRegressionMatrix(dataSet, xCols, y.size());
        regression.newSampleData(y.asDoubleArray(), x);
        this.coeffs = regression.estimateRegressionParameters();
    }


    /**
     * Predict for the given data point
     * @return the prediction
     */
    public double predict(double[] x){

        double rslt = this.coeffs[0];

        for(int i = 1; i<this.coeffs.length; ++i){
            rslt += x[i-1]*this.coeffs[i];
        }

        return rslt;
    }


    /**
     * Zero the coefficients
     */
    public void zeroCoeffs(){

        for(int c = 0; c<this.coeffs.length; ++c){
            this.coeffs[c] = 0.0;
        }
    }


    /**
     * The coefficients of the linear model
     */
    private double[] coeffs;
}
