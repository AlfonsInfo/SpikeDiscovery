package spike.solutioning.threading.ad_parallel_concurrent_problem;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTest {

    public static class Balance{
        public Balance(Long value) {
            this.value = value;
        }

        private Long value;


        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

        public static void transfer(Balance from, Balance to, Long value) throws InterruptedException{
            synchronized (from){
                Thread.sleep(1000L);
                synchronized (to){
                    Thread.sleep(1000L);
                    from.setValue(from.getValue()-value);
                    to.setValue(to.getValue()+value);
                }
            }
        }
    }


    public static class TransferWithLock {
        private static final Lock lockFrom = new ReentrantLock();
        private static final Lock lockTo = new ReentrantLock();

        public static void transfer(Balance from, Balance to, Long value) throws InterruptedException {
            try {
                // Try to acquire the locks with a timeout
                if (lockFrom.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    try {
                        if (lockTo.tryLock(1000, TimeUnit.MILLISECONDS)) {
                            try {
                                from.setValue(from.getValue() - value);
                                to.setValue(to.getValue() + value);
                            } finally {
                                lockTo.unlock();
                            }
                        } else {
                            System.out.println("Failed to acquire lock on 'to' balance");
                        }
                    } finally {
                        lockFrom.unlock();
                    }
                } else {
                    System.out.println("Failed to acquire lock on 'from' balance");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Test
    public void doDeadLockTest() throws InterruptedException {
        var balance1 = new Balance(1_000_000L);
        var balance2 = new Balance(1_000_000L);

        var thread1 = new Thread(()->{
            try {
                Balance.transfer(balance1,balance2, 900_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var thread2 = new Thread(()->{
            try {
                Balance.transfer(balance2,balance1, 500_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(balance1.getValue());
        System.out.println(balance2.getValue());
    }

    //solution ReentrantLock

    @Test
    public void doDeadLockSolutionTest() throws InterruptedException {
        var balance1 = new Balance(1_000_000L);
        var balance2 = new Balance(1_000_000L);

        var thread1 = new Thread(()->{
            try {
                TransferWithLock.transfer(balance1,balance2, 900_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var thread2 = new Thread(()->{
            try {
                TransferWithLock.transfer(balance2,balance1, 500_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(balance1.getValue());
        System.out.println(balance2.getValue());
    }
}
