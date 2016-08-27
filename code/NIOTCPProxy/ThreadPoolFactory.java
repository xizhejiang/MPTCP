package NIOTCPProxy;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by AlexJIANG on 8/28/16.
 */
public class ThreadPoolFactory<T> implements ThreadFactory<T> {

    private ExecutorService es = null;
    private int numofThreads = 1;
    ThreadPoolFactory(){
        this.numofThreads = Runtime.getRuntime().availableProcessors();
    }

    @Override
    public ExecutorService create() {
        if(null == es){
            synchronized (this){
                if(null == es){
                    this.es = Executors.newFixedThreadPool(numofThreads);
                }
            }
        }
        return es;
    }

    @Override
    public Future<T> submitTask(Callable<T> task) {
        return es.submit(task);
    }
}
