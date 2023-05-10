import java.util.concurrent.Semaphore;

class Resource {
    long countOfItems;
    static Semaphore items;
    static Semaphore spaces;
    static Semaphore mutex;

    Resource(int capacity) {
        Resource.items = new Semaphore(0);
        Resource.spaces = new Semaphore(capacity);
        Resource.mutex = new Semaphore(1);
        this.countOfItems = 0;
    }

    void decrement(String threadName) {
        try {
            {
                if (spaces.availablePermits() == 5)
                    Main.log.info(String.format("%s is waiting to consume the item. Available spaces: %d", threadName, spaces.availablePermits()));
            }
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
            {
                if (spaces.availablePermits() == 0)
                    Main.log.info(String.format("%s is waiting to produce the item. Available spaces: %d", threadName, spaces.availablePermits()));
            }
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
