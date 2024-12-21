package spike.solutioning.threading.ak_scheduler_executor_service.aj_completion_service;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    Random random = new Random();


    @Test
    void executeScheduleJobTest() throws InterruptedException {
        scheduledExecutorService.schedule(()->{
            System.out.println(System.currentTimeMillis());
        },2, TimeUnit.SECONDS);

        Thread.sleep(10000);
    }

    @Test
    void executePeriodJobTest() throws InterruptedException {
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println(System.currentTimeMillis());
        },2, 2,TimeUnit.SECONDS);

        Thread.sleep(10000);
    }
}


