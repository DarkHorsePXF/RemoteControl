package handler;

import common.CMD;
import common.Controller;
import common.RequestCode;
import net.sf.json.JSONObject;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by feng on 2015/7/18.
 */
public class SocketHandler implements Runnable {
    private Socket mSocket;


    public SocketHandler(Socket socket){
        this.mSocket=socket;
    }

    @Override
    public void run() {
        System.out.println("handling socket");
        DataInputStream dis=null;
        StringBuffer sb=new StringBuffer();
        try {
            dis=new DataInputStream(mSocket.getInputStream());
            sb.append(dis.readUTF());
            dis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("the data client request: ");
        String string=sb.toString();
        System.out.println(string);
        if (string.compareTo(RequestCode.SHUTDOWN)==0){
            Controller.getInstance().cmdShutdown();
        }
    }
}

