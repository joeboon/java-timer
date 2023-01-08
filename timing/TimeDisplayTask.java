package timing;

import java.util.Date;
import java.util.TimerTask;

public class TimeDisplayTask extends TimerTask {
    static int count = 0;
    static long baseMs;

    public TimeDisplayTask() {
        baseMs = nowMs();
    }

    @Override
    public void run() {
        long startMs = nowMs();
        count++;

        int taskExecutionMs = 1000;
        if (count == 2) {
            taskExecutionMs = 2000;
        }
        
        try {
            Thread.sleep(taskExecutionMs);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }

        long endMs = nowMs();

        long startDuration = startMs - baseMs;
        long taskDuration = endMs - startMs;

        long startQuarters = startDuration / 250L;
        long taskQuarters = taskDuration / 250L;

        for (long i = 0; i < startQuarters; i++) {
            System.out.print(' ');
        }
        
        for (long i = 0; i < taskQuarters; i++) {
            System.out.print('-');
        }

        System.out.println(" (T" + count + ")");
    }

    private long nowMs() {
        return new Date().getTime();
    }
}