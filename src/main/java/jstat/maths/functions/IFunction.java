package jstat.maths.functions;

/**
 * General interface for mathematical functions
 */
public interface IFunction<InputType, OutputType> {

    /**
     * Evaluate the function on the given input
     * @param input
     * @return
     */
    OutputType evaluate(InputType input);
}
