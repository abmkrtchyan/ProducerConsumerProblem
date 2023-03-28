import java.util.concurrent.Semaphore;

public class Producer extends Thread {
    Semaphore sem;
    String threadName;

    public Producer(Semaphore sem, String threadName) {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }

    @Override
    public void run() {
    }
}



