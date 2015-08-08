package com.dk.remotecontrol.app.common;

/**
 * Created by feng on 2015/8/7.
 */
public class NetContent {
    private String host;
    private int port=8080;

    public NetContent(String host){
        this.host=host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
