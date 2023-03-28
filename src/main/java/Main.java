public class Main {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource(5);
        Thread producer = new Producer(resource, "Producer");
        Thread consumer = new Consumer(resource, "Consumer");

        producer.join();
        consumer.join();
    }
}