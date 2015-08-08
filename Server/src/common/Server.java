package common;

import handler.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 先锋 on 2015/7/9.
 */
public class Server {
    ServerSocket serverSocket;

    ExecutorService executor= Executors.newCachedThreadPool();

    private static Server instance=new Server();
    public static Server getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Server server=Server.getInstance();
        server.start();
    }



    private Server(){
        try {
            serverSocket=new ServerSocket(SocketContent.SERVER_PORT);
            System.out.println("服务器启动！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        while (true){
            try {
                Socket socket=serverSocket.accept();
                System.out.println("连接成功！");
//                executor.execute(new SocketHandler(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
