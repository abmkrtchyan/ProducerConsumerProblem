import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
    Semaphore sem;
    String threadName;

    public Consumer(Semaphore sem, String threadName) {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }


    @Override
    public void run() {

        System.out.println("Starting " + threadName);

        try {

            // First, Y will try to get permit

            System.out.println(threadName + " awaiting permit.");

            // acquiring the lock

            sem.acquire();

            System.out.println(threadName + " received a permit.");

            // Now, accessing the shared resource and others will wait

            for (int i = 0; i < 7; i++) {

                Resource.countOfItems--;

                System.out.println(threadName + ": " + Resource.countOfItems);

                // Now, allowing a context switch -- if possible.

                // for thread X to execute

                Thread.sleep(20);

            }

        } catch (InterruptedException exc) {

            System.out.println("exc");

        }

        // Release the permit.

        System.out.println(threadName + " released permit.");

        sem.release();

    }
}