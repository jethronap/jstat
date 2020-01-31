package parallel.tasks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public abstract class TaskBase<T> implements Callable<T>, Runnable{

    /**
     * Constructor
     */
    protected TaskBase(CyclicBarrier barrier){
        this.barrier = barrier;
        this.finished = false;
        this.result = null;
        this.taskId = -1;
    }

    /**
     * Constructor
     */
    protected TaskBase(){
        this.barrier = null;
        this.finished = false;
        this.result = null;
        this.taskId = -1;
    }

    @Override
    public  T call(){

        this.run();
        return this.result;
    }

    public void waitOnBarrier(){

        // now wait until all threads are summed
        try {

            if(this.barrier == null){
                throw new IllegalStateException("Attempt to use a null barrier");
            }
            this.barrier.await();
        }
        catch(InterruptedException e){
            System.out.println("InterruptedException: "+ e.getMessage());
        }
        catch(BrokenBarrierException e){
            System.out.println("BorkenBarrierException: "+ e.getMessage());
        }
    }

    /**
     * Return true if the task is finished
     */
    public boolean isFinished(){
        return this.finished;
    }

    /**
     * Set the finished flag
     */
    public void setFinished(boolean flag){
        this.finished = flag;
    }

    /**
     * Return the result computed by the task
     */
    public T getResult(){
        return result;
    }

    /**
     * Set the result computed by the task
     */
    public void setResult(T result){
        this.result = result;
    }

    /**
     * Returns the task id
     */
    public int getTaskId(){
        return this.taskId;
    }

    /**
     * Set the the task id
     */
    public void setTaskId(int id){
        this.taskId = id;
    }

    protected CyclicBarrier barrier;
    protected boolean finished;
    protected T result;
    protected int taskId;
}
