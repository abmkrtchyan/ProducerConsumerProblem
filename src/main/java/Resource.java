import java.util.concurrent.Semaphore;

class Resource {
    long countOfItems;
    long capacity;
    static Semaphore items;
    static Semaphore spaces;
    static Semaphore mutex;

    Resource(int capacity) {
        Resource.items = new Semaphore(0);
        Resource.spaces = new Semaphore(capacity);
        Resource.mutex = new Semaphore(1);
        this.capacity = capacity;
        this.countOfItems = 0;
    }

    void decrement(String threadName) {
        try {
            items.acquire();
            mutex.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        this.countOfItems--;
        Main.log.info(String.format("%s consumed item: %d", threadName, this.countOfItems));

        mutex.release();
        spaces.release();
    }

    void increment(String threadName) {
        try {
            spaces.acquire();
            mutex.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        this.countOfItems++;
        Main.log.info(String.format("%s produced item: %d", threadName, this.countOfItems));

        mutex.release();
        items.release();
    }
}
