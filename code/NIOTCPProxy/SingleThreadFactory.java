package NIOTCPProxy;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by AlexJIANG on 8/27/16.
 */
public class SingleThreadFactory<T> implements ThreadFactory<T> {
    ExecutorService es = null;

    SingleThreadFactory(){}
    @Override
    public ExecutorService create() {
        if(null == es){
            synchronized (this){
                if(null == es){
                    this.es = Executors.newSingleThreadExecutor();
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
