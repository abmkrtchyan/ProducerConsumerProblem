import java.util.Random;

public class Consumer extends Thread {
    Resource resource;
    String threadName;

    public Consumer(Resource resource, String threadName) {
        super(threadName);
        this.resource = resource;
        this.threadName = threadName;
        this.start();
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            try {
                sleep(rand.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.resource.decrement(this.threadName);
        }
    }
}