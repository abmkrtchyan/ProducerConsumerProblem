import java.util.Random;

public class Producer extends Thread {
    Resource resource;
    String threadName;

    public Producer(Resource resource, String threadName) {
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
            this.resource.increment(this.threadName);
        }
    }
}