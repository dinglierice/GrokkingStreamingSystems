package com.zystream.ch02.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class Source extends Component {
    @Getter
    @Setter
    String name;

    public Source(String name) {
        super(name);
    }

    // TODO getEvents 从数据队列中获取数据
    public abstract void getEvent(List<Event> events);
}
