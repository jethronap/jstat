package parallel.tasks;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public class SumTask<ContainerType extends List> extends TaskBase<Double> {

    public SumTask(final List data){
        super();
        this.data = data;
        this.result = 0.0;
    }

    public SumTask(final List data, final CyclicBarrier barrier){
        super(barrier);
        this.data = data;
        this.result = 0.0;
    }

    @Override
    public  Double call(){

        this.run();
        return this.result;

    }

    @Override
    public void run(){

        for (Object item: this.data) {
            this.result += (Double) item;
        }

        this.finished = true;

        if(this.barrier != null){
            this.waitOnBarrier();
        }
    }

    public Double getResult(){
        return this.result;
    }

    private Double result;
    private List data;

}
