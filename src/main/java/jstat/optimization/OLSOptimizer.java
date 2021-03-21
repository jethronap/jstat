package jstat.optimization;

import jstat.utils.IterativeAlgorithmResult;
import jstat.maths.functions.IVectorRealFunction;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Ordinary Least Squares optimizer for a real vector function
 */
public class OLSOptimizer implements IOptimizer {

    /**
     * Parameters should be either of shape(0) = 1
     * or shape(0) = 1
     * @param parameters
     */
    public OLSOptimizer(INDArray parameters){

        this.parameters = parameters;

        if(this.parameters.size(0) == 2) {
            this.regression = new SimpleRegression(true);
        }
        else{
            this.regression = new SimpleRegression(false);
        }
    }

    /**
     * Returns the optimize parameters
     */
    @Override
    public INDArray getOptimizedParameters(){
        return this.parameters;
    }

    /**
     * Returns true if the intercept is accounted in the model
     * @return
     */
    boolean hasIntercept(){return this.regression.hasIntercept();}

    /**
     * Optimize f on the given data
     */
    public void step(INDArray data, INDArray y){

        if(data.size(1) == 2 && !hasIntercept()){
            return;
        }

        if(data.size(1) == 1 && hasIntercept()){
            return;
        }

        // convert the data into a format that
        // Apache.Math understands
        double[][] arrData = new double[(int)y.size(0)][2];

        if(data.size(1) == 2 && hasIntercept()){

            // assume that the first column is all ones
            // so only use the second column
            for(int i=0; i<data.size(0); ++i){
                arrData[i][0] = data.getDouble(i, 1);
                arrData[i][1] = y.getDouble(i);
            }
        }
        else if(data.size(1) == 1 && !hasIntercept()){

            for(int i=0; i<data.size(0); ++i) {
                arrData[i][0] = data.getDouble(i, 0);
                arrData[i][1] = y.getDouble(i);
            }
        }

        this.regression.addData(arrData);
        double slope = this.regression.getSlope();
        double intercept = this.regression.getIntercept();

        if(this.regression.hasIntercept()){

            parameters.putScalar(0, intercept);
            parameters.putScalar(1, slope);
        }
        else {
            parameters.putScalar(0, slope);
        }
    }

    private SimpleRegression regression;
    private INDArray parameters;
}
