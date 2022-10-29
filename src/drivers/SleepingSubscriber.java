package drivers;

import controller.ISubscriber;
import model.Message;

public class SleepingSubscriber implements ISubscriber {

    private String id;
    private Long sleepTime;

    public SleepingSubscriber(String id, Long sleepTime) {
        this.id = id;
        this.sleepTime = sleepTime;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        System.out.println("Subscriber: " + id + " started consuming: " + message.getMessage());
        Thread.sleep(sleepTime);
        System.out.println("Subscriber: " + id + " done consuming: " + message.getMessage());
    }
}
