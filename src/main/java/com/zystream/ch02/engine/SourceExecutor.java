package com.zystream.ch02.engine;

import com.zystream.ch02.api.Event;
import com.zystream.ch02.api.Source;

public class SourceExecutor extends ComponentExecutor{
    private final Source source;

    public SourceExecutor(Source source) {
        super(source);
        this.source = source;
    }

    @Override
    public boolean runOnce() {
        try {
            source.getEvent(eventCollector);
            for (Event event : eventCollector) {
                outgoingQueue.put(event);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void setIncomingQueue(EventQueue incomingQueue) {
        throw new RuntimeException("不允许为source算子设置上游队列");
    }
}
