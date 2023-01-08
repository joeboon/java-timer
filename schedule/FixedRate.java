package schedule;

import java.util.Timer;

public class FixedRate {
    public static void main(String args[]) throws InterruptedException {

        var timer = new Timer();
        timer.scheduleAtFixedRate(new NewsletterTask(), 0, 1000);

        Thread.sleep(3000);

        timer.cancel();
                
    }
}
