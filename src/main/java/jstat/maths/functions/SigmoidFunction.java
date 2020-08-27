package jstat.maths.functions;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class SigmoidFunction implements IVectorRealFunction {


    /**
     * Constructor
     * @param function
     */
    public SigmoidFunction(IVectorRealFunction function){

        this.function = function;
    }

    @Override
    public Double evaluate(INDArray input){

        Double value = this.function.evaluate(input);
        double expV = Math.exp(-value);
        return 1.0/(1.0 + expV);
    }

    /**
     * Set the coefficients of the function
     */
    @Override
    public final void setCoeffs(Double[] coeffs){
        this.function.setCoeffs(coeffs);
    }

    /**
     * Set the coefficients of the function
     */
    @Override
    public void setCoeffs(double[] coeffs){
        this.function.setCoeffs(coeffs);
    }

    /**
     * Returns the coefficients of the vector function
     */
    @Override
    public final INDArray getCoeffs(){
        return this.function.getCoeffs();
    }

    @Override
    public double getCoeff(int i){
        return this.function.getCoeff(i);
    }

    /**
     * Returns the number of coefficients
     */
    @Override
    public final int numCoeffs(){
        return this.function.numCoeffs();
    }

    /**
     * Returns the gradients with respect to the coefficients at the given data point
     */
    @Override
    public INDArray gradidents(INDArray data){
        INDArray rslt = data;
        rslt.putScalar(0, 1.0);
        return rslt;
    }

    /**
     * Returns the gradient with respect to the i-th variable
     * The Sigmoid is assumed to have only one variable. Hence,
     * if i != 0 this function returns 0.0
     */
    @Override
    public double gradient(int i, INDArray data){

        if(i != 0){
            return 0.0;
        }

        double z = this.function.evaluate(data);
        double expZ = Math.exp(-z);
        return expZ/((1 + expZ)*(1 + expZ)); //*this.function.gradient(i, data);
    }

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double coeffGradient(int i, INDArray data){

        double z = this.function.evaluate(data);
        double expZ = Math.exp(-z);
        return expZ/((1 + expZ)*(1 + expZ))*this.function.coeffGradient(i, data);
    }

    /**
     * Compute the gradients with respect to the coefficients
     */
    @Override
    public INDArray coeffGradients(INDArray data){

        INDArray grads = Nd4j.zeros(this.function.numCoeffs());

        for (int i = 0; i < grads.size(0); i++) {
            grads.putScalar(i, this.coeffGradient(i, data));
        }

        return grads;
    }


    private IVectorRealFunction function;
}
