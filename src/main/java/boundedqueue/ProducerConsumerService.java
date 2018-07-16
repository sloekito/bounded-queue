package boundedqueue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class ProducerConsumerService {
    private static final int QUEUE_MAX_LEN_DEFAULT = 5;
    private static final int NUMBER_OF_ELEMENTS_DEFAULT = 1000;
    private static final int NUMBER_OF_PRODUCERS_DEFAULT = 5;
    private static final int NUMBER_OF_CONSUMERS_DEFAULT = 5;
    private static final Logger LOGGER = Logger.getLogger(ProducerConsumerService.class.getName());
    public static void main(String[] args) {
        
        Map<String, Object> appProps = parseProperties();
        Integer queueMaxLength = (int) appProps.get("queueMaxLength");
        Integer numberOfElements = (int) appProps.get("numberOfElements");
        Integer numberOfProducers = (int) appProps.get("numberOfProducers");
        Integer numberOfConsumers = (int) appProps.get("numberOfConsumers");

        Queue<Object> queue = new Queue<>(queueMaxLength);

        Producer producer = new Producer(queue, numberOfElements);
        Consumer consumer = new Consumer(queue);
        // starting producer to produce messages in queue
        for (int i = 0; i < numberOfProducers; i++) {
            new Thread(producer).start();

        }
        // starting consumer to consume messages from queue
        for (int i = 0; i < numberOfConsumers; i++) {
            new Thread(consumer).start();
   
        }
    }

    static Map<String, Object> parseProperties() {
        Map<String, Object> appProps = new HashMap<>();

        String queueMaxLength = System.getProperty("queueMaxLength");

        if (queueMaxLength != null && !queueMaxLength.isEmpty()) {
            appProps.put("queueMaxLength", Integer.parseInt(queueMaxLength));

        } else {
            // Set default value
            appProps.put("queueMaxLength", QUEUE_MAX_LEN_DEFAULT);
        }

        String numberOfElements = System.getProperty("numberOfElements");

        if (numberOfElements != null && !numberOfElements.isEmpty()) {
            appProps.put("numberOfElements", Integer.parseInt(numberOfElements));

         }
         else {
            // Set default value
            appProps.put("numberOfElements", NUMBER_OF_ELEMENTS_DEFAULT);
        }
        String numberOfProducers = System.getProperty("numberOfProducers");

        if (numberOfProducers != null && !numberOfProducers.isEmpty()) {
            appProps.put("numberOfProducers", Integer.parseInt(numberOfProducers));

        } else {
            // Set default value
            appProps.put("numberOfProducers", NUMBER_OF_PRODUCERS_DEFAULT);
        }
        String numberOfConsumers = System.getProperty("numberOfConsumers");

        if (numberOfConsumers != null && !numberOfConsumers.isEmpty()) {
            appProps.put("numberOfConsumers", Integer.parseInt(numberOfConsumers));

        } else {
            // Set default value
            appProps.put("numberOfConsumers", NUMBER_OF_CONSUMERS_DEFAULT);
        }
        return appProps;
    }
}