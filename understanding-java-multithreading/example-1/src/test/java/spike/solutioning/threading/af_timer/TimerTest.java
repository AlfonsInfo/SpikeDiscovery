package spike.solutioning.threading.af_timer;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

// for delayed job or repeated job
public class TimerTest {
    @Test
    void delayedJobTest() throws InterruptedException {
        TimerTask timerTask = getTimerTask("Delayed Job");

        Timer timer = new Timer();
        timer.schedule(timerTask, 5000L);

        Thread.sleep(6000L);
    }

    @Test
    void periodicJobTest() throws InterruptedException {
        TimerTask timerTask = getTimerTask("Periodic Job");

        Timer timer = new Timer();
        timer.schedule(timerTask, 1000L,500L);

        Thread.sleep(6000L);
    }

    private static TimerTask getTimerTask(String text) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(text);
            }
        };
        return timerTask;
    }
}
