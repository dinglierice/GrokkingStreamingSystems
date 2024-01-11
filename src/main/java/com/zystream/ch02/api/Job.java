package com.zystream.ch02.api;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class Job {
    @Getter
    @Setter
    private String name;
    private Set<Source> sourceSet;

    public Job(String name) {
        this.name = name;
    }

    public void addSource(Source source) {
        if (sourceSet.contains(source)) {
            throw new RuntimeException("source already exists : " + source.getName());
        }
        sourceSet.add(source);
    }
}
