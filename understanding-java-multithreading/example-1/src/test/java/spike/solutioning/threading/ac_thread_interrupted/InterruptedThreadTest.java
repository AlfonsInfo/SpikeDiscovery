package spike.solutioning.threading.ac_thread_interrupted;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class InterruptedThreadTest {

    //untuk menginstruksikan baris kode berikutnya menunggu hasil dari sebuat thread
    @Test
    void threadInterrupted() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                if (Thread.interrupted()) {
                    return;
                }
                System.out.println(i);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        thread.interrupt();
        thread.join();
        System.out.println("Kode yang di eksekusi setelah menunggu");
    }

    @Test
    void threadState() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                if (Thread.interrupted()) {
                    return;
                }
                System.out.println(i);
            }
        };

        Thread thread = new Thread(runnable);
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());
        System.out.println("Kode yang di eksekusi setelah menunggu");
    }
}
