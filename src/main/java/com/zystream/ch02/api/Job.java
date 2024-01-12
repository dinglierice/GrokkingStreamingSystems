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

    /**
     * 新增数据源
     * @param source
     * @return 数据源的下游Stream
     */
    public Stream addSource(Source source) {
        if (sourceSet.contains(source)) {
            throw new RuntimeException("source already exists : " + source.getName());
        }
        sourceSet.add(source);
        return source.getDownStream();
    }
}
