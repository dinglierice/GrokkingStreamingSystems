package com.zystream.ch02.job;

import com.streamwork.ch02.api.Event;
import com.streamwork.ch02.api.Operator;

import java.util.*;

/**
 * @description：TODO
 * @author：dinglie
 * @date：2023/12/27 23:46
 */
public class VehicleCounter extends Operator {
    private final Map<String, Integer> countMap = new HashMap<String, Integer>();

    public VehicleCounter(String name) {
        super(name);
    }

    @Override
    public void apply(Event event, List<Event> eventCollector) {
        String vehicle = ((VehicleEvent) event).getData();
        Integer orDefault = countMap.getOrDefault(vehicle, 0);
        orDefault ++ ;
        countMap.put(vehicle, orDefault);
        System.out.println("Vehicle counter --> ");
        printCountMap();
    }

    private void printCountMap() {
        List<String> vehicles = new ArrayList<>(countMap.keySet());
        Collections.sort(vehicles);

        for (String vehicle: vehicles) {
            System.out.println("  " + vehicle + ": " + countMap.get(vehicle));
        }
    }
}
