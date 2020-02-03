package maths.functions;


import maths.Vector;

public interface IScalarRealFunction extends IRealFunction<Double>  {

    /**
     * Returns the number of coefficients
     */
    int numCoeffs();

    /**
     * Returns the coefficients of the vector function
     */
    Vector getCoeffs();

    /**
     * Set the coefficients of the function
     */
    void setCoeff(double coeff);

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    double gradient(double data);

}
