package spike.solutioning.threading.ab_creating_tread;

import org.junit.jupiter.api.Test;

public class CreateThreadTest {

    @Test
    void createThreadTest(){
        Runnable runnable = () -> {
            System.out.println("Hello from thread" + Thread.currentThread().getName());
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Test
    void simulateSleepThread() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("Hello from thread" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Sedang Tidur");
        Thread.sleep(3000);
        System.out.println("Finish");
    }

    //untuk menginstruksikan baris kode berikutnya menunggu hasil dari sebuat thread
    @Test
    void threadJoin() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("Hello from thread" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Hello 1");
        thread.join();
        System.out.println("Kode yang di eksekusi setelah menunggu");
    }
}
