package stats;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class Simple1DLinearRegression {


    /**
     * Constructor
     */
    public Simple1DLinearRegression(){}


    /**
     * Returns the interception term
     * @return
     */
    public final double getIntercept(){

        return this.intercept;
    }


    /**
     * Returns the coefficients of the regression
     */
    public final double[] getCoeffs(){

        double[] rslt = new double[1];
        rslt[0] = this.slope;
        return rslt;
    }

    /**
     * Apply 1D linear regression
     */

    public void fit(Table dataSet, String xCol, String yCol){

        // make sure xCol and yCol are in the dataSet
        if(dataSet == null){
            throw new NullPointerException("Table data set instance is NULL");
        }

        DoubleColumn xColData = dataSet.doubleColumn(xCol);
        DoubleColumn yColData = dataSet.doubleColumn(yCol);

        if(xColData == null || yColData == null){
            throw new IllegalStateException("Column: "+xCol+" or " +yCol+" not in data set");
        }

        // only one covariant so use SimpleRegression
        SimpleRegression regression = new SimpleRegression();
        this.addDataForSimpleRegression(regression, xColData, yColData);
        this.intercept = regression.getIntercept();
        this.slope = regression.getSlope();
    }

    /**
     * Predict y for the given value
     * @param value
     *
     */
    public double predict(double value){
        return this.intercept + value*this.slope;
    }


    protected void addDataForSimpleRegression(SimpleRegression simpleRegression, DoubleColumn x, DoubleColumn y){

        if(x.size() != y.size()){
            throw new IllegalArgumentException("Invalid sizes");
        }

        for(int i =0; i<x.size(); ++i){
            simpleRegression.addData(x.get(i), y.get(i));
        }
    }

    /**
     * The intercept term
     */
    private double intercept;

    /**
     * The coefficients of the linear model
     */
    private double slope;

}
