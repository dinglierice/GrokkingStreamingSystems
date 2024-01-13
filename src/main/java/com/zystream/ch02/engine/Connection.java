package com.zystream.ch02.engine;


/**
 * A util data class for connections between components.
 */
public class Connection {
    public final com.zystream.ch02.engine.ComponentExecutor from;
    public final com.zystream.ch02.engine.OperatorExecutor to;

    public Connection(ComponentExecutor from, OperatorExecutor to) {
        this.from = from;
        this.to = to;
    }
}
