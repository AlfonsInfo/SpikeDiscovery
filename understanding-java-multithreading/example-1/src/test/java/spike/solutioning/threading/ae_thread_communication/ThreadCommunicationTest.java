package spike.solutioning.threading.ae_thread_communication;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

public class ThreadCommunicationTest {
    private String message;

    /**
     * Menggunakan loop saat menunggu tidak direkomendasikan
     * karna membuang-buang resources cpu,
     * dan jika tidak terjadi interrupt loop akan berjalan terus tanpa henti
     * @throws InterruptedException
     */
    @Test
    void threadCommunicationByLoopTest() throws InterruptedException {
        AtomicReference<String> message = new AtomicReference<>();
        Thread thread = new Thread(()->{
            while(message.get() == null){}
            System.out.println(message.get());
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(2000L);
                message.set("Alfons");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();
    }

    //* By lock & notify yang tersedia di object, jadi object apapun bisa melakukannya
    @Test
    void threadCommunicationByNotifyTest() throws InterruptedException {
        final Object lock = new Object();
        Thread thread1 = new Thread(()->{
           synchronized (lock){
               try{
                   lock.wait();
                   System.out.println(message);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
        });

        Thread thread2 = new Thread(() -> {
           synchronized (lock){
               message = "Hello Youuu";
               lock.notify(); // gunakan notifyAll jika ditungguin di banyak thread
           }
        });

        thread1.start();
        thread2.start();

        thread2.join();
        thread2.join();
    }
}
