package utilities;

import resources.Consts;

import java.util.*;

public class BackUpLogTask extends TimerTask {
    public static void setTask(){
        TimerTask backUpLogTask = new BackUpLogTask();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(backUpLogTask, Consts.fDELAY,Consts.fHOW_OFTEN);
    }

    @Override public void run(){
        SimpleLogger.getLogger().backUp();
    }
}
