package com.zystream.ch02.api;
import java.util.HashSet;
import java.util.Set;

/**
 * 数据流
 * 持有下游operators
 */
public abstract class Stream {
    private final Set<Operator> operators = new HashSet<>();

    public Stream apply(Operator operator) {
        if (operators.contains(operator)) {
            throw new RuntimeException("operator already exists");
        }

        operators.add(operator);
        return operator.getDownStream();
    }
}
