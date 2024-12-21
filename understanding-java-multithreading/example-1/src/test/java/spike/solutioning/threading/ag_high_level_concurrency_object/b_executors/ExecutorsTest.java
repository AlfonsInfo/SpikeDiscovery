package spike.solutioning.threading.ag_high_level_concurrency_object.b_executors;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Hati-hati menggunakan Executors, rata-rata Threadpool yang dibuat memiliki kapasitas queue tidak terbatas
public class ExecutorsTest {

    @Test
    void createFixedThreadPool(){
        ExecutorService executors = Executors.newFixedThreadPool(100);
        executors.execute(()->{});
    }

    @Test
    void createSingleThreadExecutor(){
        ExecutorService executors = Executors.newSingleThreadExecutor();
        executors.execute(()->{});
    }

    @Test
    void cacheThreadPool(){
        ExecutorService executors = Executors.newSingleThreadExecutor();
        executors.execute(()->{});
    }
}
