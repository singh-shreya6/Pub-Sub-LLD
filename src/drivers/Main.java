package drivers;

import controller.Queue;
import model.Message;
import model.Topic;

// Test
public class Main {
    public static void main(String[] args) {
        Queue queue = new Queue();
        Topic topic1 = queue.createTopic("t1");
        Topic topic2 = queue.createTopic("t2");

        SleepingSubscriber subscriber1 = new SleepingSubscriber("s1", 1000L);
        SleepingSubscriber subscriber2 = new SleepingSubscriber("s2", 1000L);

        SleepingSubscriber subscriber3 = new SleepingSubscriber("s3", 1000L);

        queue.subscribe(subscriber1, topic1);
        queue.subscribe(subscriber2, topic1);

        queue.subscribe(subscriber3, topic2);

        queue.publish(new Message("m1") , topic1);
        queue.publish(new Message("m1") , topic2);

        queue.publish(new Message("m2") , topic2);

        queue.resetOffset(topic1, subscriber1, 0);
    }
}
