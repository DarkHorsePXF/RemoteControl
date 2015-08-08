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
            runtime.exec("shutdown -s -t 10");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
