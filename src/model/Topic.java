package model;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String topicName;
    private String topicId;
    private final List<Message> messages;
    private final List<TopicSubscriber> subscribers;

    public Topic(String topicName, String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public synchronized void addMessages(@NotNull Message message) {
        messages.add(message);
    }

    public void addSubscriber(@NotNull TopicSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<TopicSubscriber> getSubscribers() {
        return subscribers;
    }
}
