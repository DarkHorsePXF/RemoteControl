import ui.MainUI;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        InetAddress address= null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip=address.getHostAddress().toString();
        System.out.println("The ip address of server is :"+ ip);

        Server server= Server.getInstance();
        MainUI ui=new MainUI(ip);
        ui.show();
        server.start();
    }
}
