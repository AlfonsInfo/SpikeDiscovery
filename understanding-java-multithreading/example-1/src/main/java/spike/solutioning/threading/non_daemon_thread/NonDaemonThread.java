package spike.solutioning.threading.non_daemon_thread;

class MyThread extends Thread {
    public void run() {
        System.out.println("Thread biasa sedang berjalan...");
        try {
            Thread.sleep(2000);  // Simulasi tugas yang memakan waktu
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread biasa selesai.");
    }
}

public class NonDaemonThread {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        t.start();  // Memulai thread biasa
        System.out.println("Main thread selesai.");
    }
}

