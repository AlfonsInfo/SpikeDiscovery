package spike.solutioning.threading.am_locks_package;

import org.junit.jupiter.api.Test;
import spike.solutioning.threading.al_atomic_packages.AtomicExampleTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//alternatif dari sync, wait, notify
public class LockExampleTest {
    public static class CounterLock {
        private Long value = 0L;
        private final Lock lock = new ReentrantLock();
        private final ReadWriteLock writeLock = new ReentrantReadWriteLock();
        private final ReadWriteLock readLock = new ReentrantReadWriteLock();

        public void increment() {
//            writeLock.writeLock().lock();;
//             readLock.readLock().lock();
            try {
//                lock.lock();
                writeLock.writeLock().lock();
                value++;
            } finally {
                //kalo tidak di unlock tidak bisa di read
//                lock.unlock();
                writeLock.writeLock().unlock();
            }
        }

        //readlock saat get datanya kali
    }

    @Test
    void testIncrement() throws InterruptedException {
        CounterLock counter = new CounterLock();

        Runnable runnable = () -> {
            for (int i = 0; i < 10_000; i++) {
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

        System.out.println(counter.value);
    }
}
