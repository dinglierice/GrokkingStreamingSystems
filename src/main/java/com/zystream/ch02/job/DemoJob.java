package com.zystream.ch02.job;

import com.zystream.ch02.api.*;
import com.zystream.ch02.engine.JobStarter;

import java.util.List;
import java.util.Random;

/**
 * @description：TODO
 * @author：dinglie
 * @date：2024/1/13 9:10
 */
public class DemoJob {
    public static void main(String[] args) {
        // Job是对数据链的抽象
        // Job持有source, source以下通过stream串联和其他operator之间的关系
        Job job = new Job("测试作业");
        Stream stream =  job.addSource(new Source("测试数据源") {
            public final Random random = new Random();
            @Override
            public void getEvent(List<Event> events) {
                events.add(new Event() {
                    @Override
                    public Integer getData() {
                        return random.nextInt();
                    }
                });
            }
        });

        // Stream apply operator, operator holds downstream
        stream.apply(new Operator("测试算子") {
            @Override
            public void apply(Event event, List<Event> eventCollector) {
                System.out.println(event);
            }
        });

        JobStarter jobStarter = new JobStarter(job);
        jobStarter.start();
    }
}
