package spike.solutioning.threading.ah_future;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import spike.solutioning.threading.LogRejectedExecutionHandler;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class FutureTest {
    @Test
    void testFuture() throws InterruptedException, ExecutionException {
        ExecutorService executors = Executors.newFixedThreadPool(100);
        Future<String> stringFuture= executors.submit(getStringCallable());
        while (!stringFuture.isDone()){
            System.out.println("Waiting Result");
            Thread.sleep(1000L);
        }
        System.out.println(stringFuture.get());
    }

    @Test
    void testFutureGotCancelled() throws InterruptedException, ExecutionException {
        ExecutorService executors = Executors.newFixedThreadPool(100);
        Future<String> stringFuture= executors.submit(getStringCallable());
        int i = 0;
        while (!stringFuture.isDone()){
            System.out.println("Waiting Result");
            Thread.sleep(1000L);
            i++;
            if(i==4){
                stringFuture.cancel(true);
            }
        }
        if(!stringFuture.isCancelled())
        {
            System.out.println(stringFuture.get());
        }else{
            System.out.println("Result Cancelled");
        }
    }


    @Test
    void testInvokeAllCallable() throws InterruptedException, ExecutionException {
        ExecutorService executors = Executors.newFixedThreadPool(100);
        List<Future<String>> stringFuture= executors.invokeAll(getListCallables());

        stringFuture.forEach(stringFuture1 -> {
            try {
                System.out.println(stringFuture1.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Task Failed");
            } finally {
                executors.shutdown();
            }
        });
    }

    //resulntya merupakan callable yang paling cepat mendapatkan value
    @Test
    void testInvokeAnyCallable() throws InterruptedException, ExecutionException {
        ExecutorService executors = Executors.newFixedThreadPool(100);
        String stringFuture= executors.invokeAny(getListCallables());
        System.out.println(stringFuture);
    }

    private static Callable<String> getStringCallable() {
        return () -> {
            Thread.sleep(5000L);
            return "Hello You";
        };
    }

    private static List<Callable<String>> getListCallables(){
        return IntStream.range(1,11).mapToObj(operand ->(Callable<String>) () ->{
            Thread.sleep(operand * 500L);
            return String.valueOf(operand);
        }).toList();
    }


}
