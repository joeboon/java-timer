package schedule;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

public class NewsletterTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("Email sent at: " 
          + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), 
          ZoneId.systemDefault()));
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
    }
}