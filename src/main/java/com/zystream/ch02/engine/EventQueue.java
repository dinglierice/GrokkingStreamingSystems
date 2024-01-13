package com.zystream.ch02.engine;

import com.zystream.ch02.api.Event;

import java.util.concurrent.ArrayBlockingQueue;

public class EventQueue extends ArrayBlockingQueue<Event> {
    public EventQueue(int capacity) {
        super(capacity);
    }

    private static final long serialVersionUID = 3673430816396808407L;
}
