package schedule;

import java.util.Timer;

public class FixedDelay {
    public static void main(String args[]) throws InterruptedException {

        new Timer().schedule(new NewsletterTask(), 0, 1000);

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
        }
                
    }
}
