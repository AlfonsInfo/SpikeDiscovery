package spike.solutioning.threading.aa_main_thread;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


public class MainThreadTest {
    @Test
    void mainThread(){
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName);
        Assert.hasText(threadName, "main");
    }
}
