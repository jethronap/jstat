package parallel.tasks;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class SumSqrDiffTask<ContainerType extends List> extends SumSqrTask<ContainerType> {

    public SumSqrDiffTask(final List data, double val){
        super(data);
        this.val = val;
    }

    public SumSqrDiffTask(final List data, CyclicBarrier barrier, double val){
        super(data, barrier);
        this.val = val;
    }

    @Override
    public void run(){

        for (Object item: super.getData()) {
            Double val = (Double) item;
            this.result += (val - this.val)*(val - this.val);
        }

        this.finished = true;

        if(this.barrier != null){
            this.waitOnBarrier();
        }
    }

    private double val;
}
