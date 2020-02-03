package maths.functions;

public interface IFunction<InputType, OutputType> {

    OutputType evaluate(InputType input);
}
