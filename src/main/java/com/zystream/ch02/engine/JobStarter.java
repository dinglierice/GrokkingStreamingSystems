package com.zystream.ch02.engine;

import com.zystream.ch02.api.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JobStarter {
    private final static int QUEUE_SIZE = 64;
    private Job job;
    public JobStarter(Job job) {
        this.job = job;
    }
    
    private final List<ComponentExecutor> componentExecutorList = new ArrayList<>();
    
    private final List<Connection> connections = new ArrayList<>();

    public void start() {
        setupComponentExecutors();
        setupConnections();
        startProcesses();
    }

    private void startProcesses() {
        Collections.reverse(componentExecutorList);
        for (ComponentExecutor componentExecutor : componentExecutorList) {
            componentExecutor.start();
        }
    }

    private void setupConnections() {
        for (Connection connection : connections) {
            connectExecutors(connection);
        }
    }

    private void setupComponentExecutors() {
        Collection<Source> sources = job.getSources();
        for (Source source : sources) {
            SourceExecutor sourceExecutor = new SourceExecutor(source);
            componentExecutorList.add(sourceExecutor);
            traverseComponent(source, sourceExecutor);
        }
    }

    private void connectExecutors(com.zystream.ch02.engine.Connection connection) {
        com.zystream.ch02.engine.EventQueue intermediateQueue = new EventQueue(QUEUE_SIZE);
        connection.from.setOutgoingQueue(intermediateQueue);
        connection.to.setIncomingQueue(intermediateQueue);
    }

    private void traverseComponent(Component component, com.zystream.ch02.engine.ComponentExecutor executor) {
        Stream stream = component.getDownStream();

        for (Operator operator: stream.getAppliedOperators()) {
            com.zystream.ch02.engine.OperatorExecutor operatorExecutor = new OperatorExecutor(operator);
            componentExecutorList.add(operatorExecutor);
            connections.add(new com.zystream.ch02.engine.Connection(executor, operatorExecutor));
            // Setup executors for the downstream operators.
            traverseComponent(operator, operatorExecutor);
        }
    }
}
