import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public class Main {
    public static final String ROOT_DIR = System.getProperty("user.dir");
    public static final String LOG_4_J_PROPERTIES = ROOT_DIR + File.separator + "log4j.properties";
    static Logger log = Logger.getLogger(Resource.class.getName());


    public static void main(String[] args) throws InterruptedException {
        PropertyConfigurator.configure(LOG_4_J_PROPERTIES);
        Resource resource = new Resource(5);
        Thread producer = new Producer(resource, "Producer");
        Thread consumer = new Consumer(resource, "Consumer");

        producer.join();
        consumer.join();
    }
}