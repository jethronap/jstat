package utils;

public class DefaultIterativeAlgorithmController implements IterativAlgorithmController {

    /**
     * Constructor
     */
    public DefaultIterativeAlgorithmController(int maxIterations, double exitTolerance){
        this.maxIterations = maxIterations;
        this.exitTolerance = exitTolerance;
        this.currentIterationIdx = 0;
        this.currentRes = Double.MAX_VALUE;
    }

    /**
     * Returns true if the iterations of the algorithm should be continued
     */
    @Override
    public boolean continueIterations(){

        if(this.currentIterationIdx >= this.maxIterations){
            return false;
        }
        else if(this.currentRes < this.exitTolerance){

            this.currentIterationIdx++;
            return false;
        }

        this.currentIterationIdx++;
        return true;
    }

    /**
     * Returns the current iteration index
     */
    @Override
    public int getCurrentIteration(){
        return this.currentIterationIdx;
    }

    /**
     * Returns the exit tolerance for the algorithm
     */
    @Override
    public double getExitTolerance(){
        return this.exitTolerance;
    }

    /**
     * Returns the state of the controller
     */
    @Override
    public IterativeAlgorithmResult getState(){

        IterativeAlgorithmResult result = new IterativeAlgorithmResult();
        result.converged = this.currentRes <= this.exitTolerance? true:false;
        result.numIterationsNeeded = this.currentIterationIdx;
        result.tolerance = this.currentRes;
        return result;

    }

    /**
     * Update residual
     */
    @Override
    public void updateResidual(double res){

        this.currentRes = res;
    }

    private int maxIterations;
    private double exitTolerance;
    private int currentIterationIdx;
    private double currentRes;
}
