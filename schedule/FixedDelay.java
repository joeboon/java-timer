package schedule;

import java.util.Timer;

public class FixedDelay {
    public static void main(String args[]) throws InterruptedException {

        var timer = new Timer();
        timer.schedule(new NewsletterTask(), 0, 1000);

        Thread.sleep(3000);

        timer.cancel();

    }
}
