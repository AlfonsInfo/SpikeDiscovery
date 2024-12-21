package spike.solutioning.threading.al_atomic_packages;

import org.junit.jupiter.api.Test;
import spike.solutioning.threading.ad_parallel_concurrent_problem.RaceConditionTest;

import java.util.concurrent.atomic.AtomicLong;

//Package atomic berisi class" yang mendukung lock free dan thread safe programming single variable
// Implementasi Compare dan swap yang mendukung synchronization, dengan atomic tidak perlu sync manual lagi
public class AtomicExampleTest {

    public static class CounterAtomic{
        private AtomicLong value = new AtomicLong(0L);

        public void increment(){
            value.incrementAndGet();
        }
    }

    @Test
    void testIncrement() throws InterruptedException {
        CounterAtomic counter = new CounterAtomic();

        Runnable runnable = () -> {
            for (int i = 0 ; i < 10_000 ; i++){
                System.out.println("Proses dilakukan thread : " + Thread.currentThread().getName());
                counter.increment();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.value.get());
    }



}
