package spike.solutioning.threading;

public class ThreadMemoryCost {
    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();

        // Bersihkan garbage collector untuk hasil pengukuran yang lebih akurat
        System.gc();
        Thread.sleep(1000); // Tunggu sebentar untuk GC selesai

        // Ambil penggunaan memori sebelum membuat thread
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // Membuat dan menjalankan thread
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulasi pekerjaan
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread.join(); // Tunggu thread selesai

        // Ambil penggunaan memori setelah membuat thread
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        // Hitung penggunaan memori oleh thread
        long memoryUsedBytes = memoryAfter - memoryBefore;
        // Konversi ke MB
        double memoryUsedMB = memoryUsedBytes / (1024.0 * 1024.0);

        System.out.printf("Memori yang digunakan oleh 1 thread: %.2f MB%n", memoryUsedMB);

    }
}

