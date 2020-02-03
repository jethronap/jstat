package maths.functions;

/**
 * General interface for defining regularization functions
 */
public interface IRegularizerFunction extends IFunction<Void, Double> {

    /**
     * Returns the value of the regularizer
     */
    Double evaluate(Void input);
}
