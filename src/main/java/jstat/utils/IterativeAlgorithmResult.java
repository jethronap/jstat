package jstat.utils;

public class IterativeAlgorithmResult  {

    public boolean converged=false;
    public double tolerance;
    public int numThreadsUsed;
    public int numIterationsNeeded;

    @Override
    public String toString(){

        StringBuilder bd = new StringBuilder();
        bd.append("Converged: "+(Boolean.toString(converged))+"\n");
        bd.append("Tolerance: "+(Double.toString(tolerance))+"\n");
        bd.append("# Threads: "+(Integer.toString(numThreadsUsed))+"\n");
        bd.append("Iterations: "+(Integer.toString(numIterationsNeeded))+"\n");

        return bd.toString();
    }

}
