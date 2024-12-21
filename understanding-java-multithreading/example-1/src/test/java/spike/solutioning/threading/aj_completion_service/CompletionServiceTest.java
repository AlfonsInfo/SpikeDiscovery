package spike.solutioning.threading.aj_completion_service;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Random random = new Random();
    //interface memisahkan antara async task dan yang menerima hasil dari task tersebut
    CompletionService<String> service = new ExecutorCompletionService<>(executorService);

    @Test
    void sendAndPollTaskToCompletionService() throws InterruptedException {
        Executors.newSingleThreadExecutor().execute(() -> {
            for(int i = 0 ; i < 100 ; i++ ){
                final int task = i;
                service.submit(() -> {
                   Thread.sleep(random.nextInt(2000));
                   return "Task-"+task;
                });
            }
        });

        Executors.newSingleThreadExecutor().execute(() -> {
            while (true){
                try {
                    Future<String> future = service.poll(5, TimeUnit.SECONDS);
                    if(future == null){
                        break;
                    }else{
                        System.out.println(future.get());
                    }
                } catch (InterruptedException | ExecutionException e) {
                    break;
                }
            }
        });

        Thread.sleep(10000L);
    }
}


