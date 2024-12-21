package spike.solutioning.threading.daemon_thread;

class DaemonThread extends Thread {
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);  // Tidur sejenak
                //Tidak pernah dieksekusi karna main thread tidak akan menunggu proses ini
                System.out.println("Daemon thread sedang berjalan...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Complete");
        }
    }
}

public class DaemonThreadExample {
    public static void main(String[] args) throws InterruptedException {
        DaemonThread daemon = new DaemonThread();
        daemon.setDaemon(true);  // Menetapkan thread ini sebagai daemon
        daemon.start();  // Memulai daemon thread
        System.out.println("Main thread selesai.");
    }
}
