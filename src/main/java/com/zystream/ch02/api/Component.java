package com.zystream.ch02.api;

import lombok.Getter;
import lombok.Setter;

public class Component {
    private Stream downStream;

    @Setter
    @Getter
    private String name;

    public Component(String name) {
        this.name = name;
    }

    public Stream getDownStream() {
        return this.downStream;
    }
}
