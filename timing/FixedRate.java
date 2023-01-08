package timing;

import java.util.Timer;

public class FixedRate {
    public static void main(String args[]) throws InterruptedException {

        System.out.println("|---|---|---|---|---|---|");
        System.out.println("0s  1s  2s  3s  4s  5s  6s");
        
        var timer = new Timer();
        timer.scheduleAtFixedRate(new TimeDisplayTask(), 0, 2000);

        Thread.sleep(7000);

        timer.cancel();
                
    }
}
