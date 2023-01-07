package schedule;

import java.util.Timer;

public class FixedRate {
    public static void main(String args[]) throws InterruptedException {

        new Timer().scheduleAtFixedRate(new NewsletterTask(), 0, 1000);

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
        }
                
    }
}
