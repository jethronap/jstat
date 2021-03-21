package jstat.maths.functions;

import org.nd4j.linalg.api.ndarray.INDArray;

public interface IRealFunction<InputType> extends IFunction<InputType, Double> {

    /**
     * Returns the number of coefficients
     */
    int numCoeffs();

    /**
     * Returns the coefficients of the vector function
     */
    INDArray getCoeffs();

    /**
     * Returns the i-th coefficient
     */
    double getCoeff(int i);

    /**
     * Set the coefficients of the function
     */
    void setCoeffs(Double[] coeffs);


    /**
     * Set the coefficients of the function
     */
    void setCoeffs(double[] coeffs);

    /**
     * Set the coefficents of the function
     */
    default void setCoeffs(INDArray coeffs){this.setCoeffs(coeffs.toDoubleVector());}

}
