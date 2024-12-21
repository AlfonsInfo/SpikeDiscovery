package spike.solutioning.threading.ag_high_level_concurrency_object;

import org.junit.jupiter.api.Test;
import spike.solutioning.threading.LogRejectedExecutionHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest{
    @Test
    void testCreateThreadPool(){
        //representasi thread pool di java adalah ThreadPoolExecutor
        int minThread = 10;
        int maxThread = 100;
        int alive = 1;
        TimeUnit timeUnit = TimeUnit.MINUTES;

        ArrayBlockingQueue<Runnable> queues= new ArrayBlockingQueue<>(100);
        Executor executor = new ThreadPoolExecutor(minThread,maxThread,alive,timeUnit,queues);
    }

    @Test
    void testExecuteRunnable() throws InterruptedException {
        //representasi thread pool di java adalah ThreadPoolExecutor
        int minThread = 10;
        int maxThread = 100;
        int alive = 1;
        TimeUnit timeUnit = TimeUnit.MINUTES;

        ArrayBlockingQueue<Runnable> queues= new ArrayBlockingQueue<>(100);
        Executor executor = new ThreadPoolExecutor(minThread,maxThread,alive,timeUnit,queues);

        executor.execute(()->{
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread.sleep(1000L);
    }

    @Test
    void testStopThreadPool() throws InterruptedException {
        //representasi thread pool di java adalah ThreadPoolExecutor
        int minThread = 10;
        int maxThread = 100;
        int alive = 1;
        TimeUnit timeUnit = TimeUnit.MINUTES;

        ArrayBlockingQueue<Runnable> queues= new ArrayBlockingQueue<>(100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread,maxThread,alive,timeUnit,queues);

        executor.execute(()->{
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        // another : shutdown , shutdownNow
        executor.awaitTermination(1, TimeUnit.HOURS);
        Thread.sleep(1000L);
    }


    @Test
    void testHandleRejected() throws InterruptedException {
        //representasi thread pool di java adalah ThreadPoolExecutor
        int minThread = 10;
        int maxThread = 100;
        int alive = 1;
        TimeUnit timeUnit = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queues= new ArrayBlockingQueue<>(10);
        //Handling Rejected
        LogRejectedExecutionHandler rejectedExecutionHandler = new LogRejectedExecutionHandler();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread,maxThread,alive,timeUnit,queues, rejectedExecutionHandler);

        for(int i = 0 ; i<1000;i++){
            final var task = i;
            executor.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("Task " + task + " from thread : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.HOURS);
        Thread.sleep(10000L);
    }
}
