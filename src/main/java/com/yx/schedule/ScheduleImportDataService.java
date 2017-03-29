package com.yx.schedule;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 定时操作
 */
public class ScheduleImportDataService {

    private String server;
    private int port;
    private String appName;
    private String collectionName;
    private String requestHandler;

    public ScheduleImportDataService(String server, int port, String appName, String collectionName, String requestHandler) {
        this.server = server;
        this.port = port;
        this.appName = appName;
        this.collectionName = collectionName;
        this.requestHandler = requestHandler;
    }

    public void importData() {
        if (StringUtils.isBlank(requestHandler)) {
            System.out.println("config error!");
            return;
        }
        try {
            URL url;
            URLConnection connection;
            String[] handlers = requestHandler.split(",");
            StringBuffer sb = new StringBuffer("http://");
            sb.append(server).append(":").append(port).append("/").append(appName).append("/").append(collectionName).append("/");
            String params = "?command=delta-import&clean=false&commit=true";

            for (int i = 0; i < handlers.length; i++) {
                url = new URL(sb.append(handlers[i]).append(params).toString());
                connection = url.openConnection();
                connection.connect();
                connection.getInputStream();
            }
            System.out.println("--------------------");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
