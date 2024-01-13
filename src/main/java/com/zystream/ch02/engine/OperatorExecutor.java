package com.zystream.ch02.engine;

import com.zystream.ch02.api.Event;
import com.zystream.ch02.api.Operator;

public class OperatorExecutor extends ComponentExecutor {

    private final Operator operator;
    public OperatorExecutor(Operator operator) {
        super(operator);
        this.operator = operator;
    }

    @Override
    public boolean runOnce() {
        try {
            Event take = incomingQueue.take();
            operator.apply(take, eventCollector);
            for (Event event : eventCollector) {
                outgoingQueue.put(event);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
