package jstat.maths.functions;


import jstat.datasets.VectorDouble;
import org.nd4j.linalg.api.ndarray.INDArray;

public interface IScalarRealFunction extends IRealFunction<Double>  {

    /**
     * Returns the number of coefficients
     */
    int numCoeffs();

    /**
     * Returns the coefficients of the vector function
     */
    INDArray getCoeffs();

    /**
     * Set the coefficients of the function
     */
    void setCoeff(double coeff);

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    double gradient(double data);

}
