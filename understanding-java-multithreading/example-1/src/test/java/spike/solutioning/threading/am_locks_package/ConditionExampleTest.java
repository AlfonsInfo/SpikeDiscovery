package spike.solutioning.threading.am_locks_package;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.*;

// condition memiliki method wait, signal, signalAll
public class ConditionExampleTest {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private String message;

    @Test
    void testCommunication() throws InterruptedException {
        Thread thread1 = new Thread(()->{
            try{
                lock.lock();
                condition.await();
                System.out.println(message);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
           try{
                lock.lock();
                message = "Hello Youuu";
                condition.signal();
            }finally {
               lock.unlock();
           }
        });

        thread1.start();
        thread2.start();

        thread2.join();
        thread2.join();
    }
}
