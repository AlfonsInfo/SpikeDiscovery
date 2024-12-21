package spike.solutioning.threading.ad_parallel_concurrent_problem;

import org.junit.jupiter.api.Test;

public class RaceConditionTest {

    public static class Counter{
        private Long value = 0L;

        public void increment(){
            // Membuat salinan dari nilai value sebelum diubah
            Long valueClone = value;

            // Mencetak nilai sebelum dan sesudah increment
            System.out.println("Value sekarang = " + value + ", value sesudah = " + (value + 1));

            // Melakukan increment (penambahan) pada value
            value++;
            System.out.println("Value setelah increment = " + value);
        }

        public  synchronized void incrementSynchronized(){
            value++;
        }
        public Long getValue(){
            return value;
        }

        public void incrementSynchronizedStatement(){
            // Membuat salinan dari nilai value sebelum diubah
            Long valueClone = value;
            // Mencetak nilai sebelum dan sesudah increment
            System.out.println("Value sekarang = " + value + ", value sesudah = " + (value + 1));
            // Melakukan increment (penambahan) pada value
            synchronized (this){
                value++;
            }
            System.out.println("Value setelah increment = " + value);
        }

    }

    //Problem
    @Test
    void testDoRaceCondition() throws InterruptedException {
        Counter counter = new Counter();

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

        System.out.println(counter.getValue());
    }

    //Solution
    /**
     * 1. Solusi dari race condition bisa menggunakan synchronization
     * 2. Synchronization merupakan fitur yang memaksa kode program hanya bisa diakses 1 thread saja
     * 3. Dampaknya prosesnya lebih lama
     * 4. Dengan menggunakan synchronization lebih aman dari race condition
     */
    @Test
    void testDoSolutionForRaceCondition() throws InterruptedException {
        Counter counter = new Counter();

        Runnable runnable = () -> {
            for (int i = 0 ; i < 10_000 ; i++){
                System.out.println("Proses dilakukan thread : " + Thread.currentThread().getName());
                counter.incrementSynchronized();
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

        System.out.println(counter.getValue());
    }

    @Test
    void testDoSolutionForRaceCondition2() throws InterruptedException {
        Counter counter = new Counter();

        Runnable runnable = () -> {
            for (int i = 0 ; i < 10_000 ; i++){
                System.out.println("Proses dilakukan thread : " + Thread.currentThread().getName());
                counter.incrementSynchronizedStatement();
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

        System.out.println(counter.getValue());
    }
}
