package com.zystream.ch02.engine;

import com.zystream.ch02.api.Component;
import com.zystream.ch02.api.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class ComponentExecutor extends Process{
    /**
     * 下游operator会有用到
     * 临时的中间存储, 取出然后写入下游队列
     */
    protected final List<Event> eventCollector = new ArrayList<>();

    @Setter
    protected EventQueue incomingQueue = null;

    @Setter
    protected EventQueue outgoingQueue = null;

    @Getter
    private final Component component;

    public ComponentExecutor(Component component) {
        this.component = component;
    }

}
