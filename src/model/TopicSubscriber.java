package model;

import controller.ISubscriber;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber {

    private AtomicInteger offset;
    private final ISubscriber subscriber;

    public AtomicInteger getOffset() {
        return offset;
    }

    public void setOffset(AtomicInteger offset) {
        this.offset = offset;
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }

    public TopicSubscriber(ISubscriber subscriber) {
        this.offset = new AtomicInteger(0);
        this.subscriber = subscriber;
    }
}
