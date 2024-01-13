package com.zystream.ch02.api;


import java.util.List;

public abstract class Operator extends Component{
    public Operator(String name) {
        super(name);
    }

    /**
     * 算子逻辑
     * @param event: incoming event
     * @param eventCollector : outgoing event collector
     */
    public abstract void apply(Event event, List<com.zystream.ch02.api.Event> eventCollector);
}
