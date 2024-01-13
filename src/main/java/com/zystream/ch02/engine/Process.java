package com.zystream.ch02.engine;

/**
 * Executor的基类
 * Executor负责创建线程、执行算子逻辑
 */
public abstract class Process {
    private final Thread thread;

    public Process() {
        this.thread = new Thread() {
            @Override
            public void run() {
                while (runOnce());
            }
        };
    }

    public void start() {
        this.thread.start();
    }

    public abstract boolean runOnce();
}
