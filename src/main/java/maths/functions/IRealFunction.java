package maths.functions;

import maths.IVector;
import maths.Vector;

public interface IRealFunction<InputType> extends IFunction<InputType, Double> {

    /**
     * Returns the number of coefficients
     */
    int numCoeffs();

    /**
     * Returns the coefficients of the vector function
     */
    IVector<Double> getCoeffs();

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
    default void setCoeffs(IVector<Double> coeffs){this.setCoeffs(coeffs.toArray());}

}
