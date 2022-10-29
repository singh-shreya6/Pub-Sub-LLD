package controller;

import handler.TopicHandler;
import model.Message;
import model.Topic;
import model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
We have to design a message queue supporting publisher-subscriber model. It should support following operations:

1. It should support multiple topics where messages can be published.
2. Publisher should be able to publish a message to a particular topic.
3. Subscribers should be able to subscribe to a topic.
4. Whenever a message is published to a topic, all the subscribers, who are subscribed to that topic, should receive the message.
5. Subscribers should be able to run in parallel
 */
public class Queue {

    private final Map<String, TopicHandler>  topicHandlers;

    public Queue() {
        topicHandlers = new HashMap<>();
    }

    public Topic createTopic(final String topicName) {
        Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlers.put(topic.getTopicId(), topicHandler);
        System.out.println("Created topic: " + topicName);
        return topic;
    }

    public void subscribe(ISubscriber subscriber, Topic topic) {
        topic.addSubscriber(new TopicSubscriber(subscriber));
        System.out.println("Subscriber Added: " + subscriber.getId() + " to topic: " + topic.getTopicName());
    }

    public void publish(Message message, Topic topic) {
        topic.addMessages(message);
        System.out.println("Message: " + message.getMessage() + " published to topic: " + topic.getTopicName());
        new Thread(() -> topicHandlers.get(topic.getTopicId()).publish(topic)).start();
    }

    public void resetOffset(final Topic topic, final ISubscriber subscriber, final Integer newOffset) {
        for (TopicSubscriber topicSubscriber : topic.getSubscribers()) {
            if (topicSubscriber.getSubscriber().equals(subscriber)) {
                topicSubscriber.getOffset().set(newOffset);
                System.out.println(topicSubscriber.getSubscriber().getId() + " offset reset to: " + newOffset);
                new Thread(() -> topicHandlers.get(topic.getTopicId()).startSubscriberWorker(topicSubscriber)).start();
                break;
            }
        }
    }
}
