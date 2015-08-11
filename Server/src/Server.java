import common.Controller;
import common.SocketContent;
import handler.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by feng on 2015/7/9.
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
            System.out.println("Server launched!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        //Shutdown in 2 hours if the client forget to shutdown
        Controller.getInstance().cmdShutdownIn2hours();
        while (true){
            try {
                Socket socket=serverSocket.accept();
                System.out.println("Socket connect successfully!");
                executor.execute(new SocketHandler(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
