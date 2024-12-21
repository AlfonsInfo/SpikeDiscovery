package spike.solutioning.threading.ai_completable_future;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureTest {
    Random random = new Random();
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CompletableFuture<String> getValue() {
        CompletableFuture<String> future = new CompletableFuture<>();
        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                future.complete("Result Future");
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    private void execute(CompletableFuture<String> future, String value) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                Thread.sleep(2000 + random.nextInt(5000));
                future.complete(value);
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });
    }

    private CompletableFuture<String> getFastest() {
        CompletableFuture<String> future = new CompletableFuture<>();
        execute(future, "Thread 1");
        execute(future, "Thread 2");

        return future;
    }

    @Test
    void testGetValue() throws ExecutionException, InterruptedException {
        Future<String> future = getValue();
        System.out.println(future.get());
    }

    @Test
    void testGetFastest() throws ExecutionException, InterruptedException {
        Future<String> resultGetFastest = getFastest();
        System.out.println("Start");
        System.out.println(resultGetFastest.get());
        System.out.println("End");
    }

    @Test
    void testUsingCompletionStage() throws ExecutionException, InterruptedException {
        CompletableFuture<String[]> future = getValue()
                .thenApply(String::toUpperCase)
                        .thenApply(v->v.split(" "));
        String[] results = future.get();
        for( String result : results ){
            System.out.println(result);
        }
    }
}
