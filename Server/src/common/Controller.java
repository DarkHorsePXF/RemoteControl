package common;

import java.io.IOException;

/**
 * Created by feng on 2015/8/7.
 */
public class Controller {
    private static Controller instance=new Controller();
    private Runtime runtime;



    private Controller(){
        runtime=Runtime.getRuntime();
    }

    public static Controller getInstance(){
        return instance;
    }

    public boolean cmdShutdown(){
        try {
            runtime.exec(CMD.SHUTDOWN_IN_10s);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cmdShutdownIn2hours(){
        try {
            runtime.exec(CMD.SHUTDOWN_IN_2h);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cmdCancelShutDown(){
        try {
            runtime.exec(CMD.CANCEL_SHUTDOWN);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
