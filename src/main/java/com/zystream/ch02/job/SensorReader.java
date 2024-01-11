package com.zystream.ch02.job;

import com.streamwork.ch02.api.Event;
import com.streamwork.ch02.api.Source;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @description：定义一个事件输入器
 * @author：dinglie
 * @date：2023/12/27 23:40
 */
public class SensorReader extends Source {
    private final BufferedReader bufferedReader;
    public SensorReader(String name, int port) {
        super(name);
        bufferedReader = setupSocketReader(port);
    }

    @Override
    public void getEvents(List<Event> eventCollector) {
        try {
            String vehicle = bufferedReader.readLine();
            eventCollector.add(new VehicleEvent(vehicle));
            System.out.println("SensorReader --> " + vehicle);
        } catch (Exception e) {
            // ignore
        }
    }

    private BufferedReader setupSocketReader(int port) {
        return getBufferedReader(port);
    }

    @NotNull
    public static BufferedReader getBufferedReader(int port) {
        try {
            // 监听本地输入
            Socket socket = new Socket("localhost", port);
            InputStream input = socket.getInputStream();
            return new BufferedReader(new InputStreamReader(input));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
}
