package utilities;

import resources.Consts;
import resources.KeyInfo;
import resources.ListFixedLengthWithPredicate;

import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import static resources.Consts.*;

public class KeyTimer {
    private static KeyTimer keyTimer;
    private ArrayList<KeyInfo> listOfKeyInfos = new ArrayList<KeyInfo>();
    public  ArrayList<ListFixedLengthWithPredicate> ListOfListOfAverages;

    private KeyTimer(){
        listOfKeyInfos = new ArrayList<KeyInfo>();
        ListOfListOfAverages = new ArrayList<ListFixedLengthWithPredicate>();
        ListOfListOfAverages.add(new ListFixedLengthWithPredicate<Double>(FixedListSize, isChar(Consts.CHAR_STRING), Consts.CHAR_STRING));
        ListOfListOfAverages.add(new ListFixedLengthWithPredicate<Double>(FixedListSize, isMovement(), "MOVEMENT"));
        ListOfListOfAverages.add(new ListFixedLengthWithPredicate<Double>(FixedListSize, isChar(Consts.BACKSPACE_STRING), Consts.BACKSPACE_STRING));
        ListOfListOfAverages.add(new ListFixedLengthWithPredicate<Double>(FixedListSize, isChar(Consts.SPACE_STRING), Consts.SPACE_STRING));
        ListOfListOfAverages.add(new ListFixedLengthWithPredicate<Double>(FixedListSize, isChar(Consts.ENTER_STRING), Consts.ENTER_STRING));
        CheckAverages checkAverages = new CheckAverages();
        java.util.Timer timer = new Timer();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(checkAverages, AVERAGES_COLLECTING_TIME , AVERAGES_COLLECTING_TIME, TimeUnit.SECONDS);
    }

    public static KeyTimer getKeyTimer(){
        if(keyTimer == null){
            keyTimer = new KeyTimer();
        }
        return keyTimer;
    }

    public double getKeyPerTime(int seconds, Predicate p) {
        int count = 0;
        long Time = System.nanoTime();
        long StartTime = Time - seconds * (long)Math.pow(10, 9);
        for (KeyInfo key: listOfKeyInfos) {
            if(p.test(key.typeOfKey) && key.keytime >= StartTime ) count ++;
        }
        return count;
    }

    public void showDialogBox(String Stats){
        JPanel panel = new EmotionsForm.MyPanel();
        ((EmotionsForm.MyPanel) panel).OpenDialogWindow(Stats);
    }

    public boolean checkIfShow(ListFixedLengthWithPredicate<Double> list, int Seconds){
        boolean ifShow = false;
        if (list.size()>= FixedListSize) {
            if (getKeyPerTime(Seconds,list.predicate)> (double) Collections.max(list)) ifShow = true;
        }
        return ifShow;
    }
    public void addKey (long time, String type) {
        listOfKeyInfos.add(new KeyInfo(time, type));
    }
}
