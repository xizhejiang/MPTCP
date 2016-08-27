package NIOTCPProxy;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by AlexJIANG on 8/27/16.
 */
public interface ThreadFactory<T> {
    ExecutorService create();
    Future <T> submitTask(Callable<T> task);
}
