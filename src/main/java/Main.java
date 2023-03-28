import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(5);
        Producer producer = new Producer(sem, "producer");
        Consumer consumer = new Consumer(sem, "consumer");

        producer.start();
        consumer.start();
    }
}