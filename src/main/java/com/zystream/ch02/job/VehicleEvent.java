package com.zystream.ch02.job;

import com.streamwork.ch02.api.Event;

/**
 * @description：定义事件实体
 * @author：dinglie
 * @date：2023/12/27 23:39
 */
public class VehicleEvent extends Event {
    private final String vehicle;

    public VehicleEvent(String vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String getData() {
        return this.vehicle;
    }
}
