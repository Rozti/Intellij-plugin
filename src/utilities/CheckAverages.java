package utilities;



import utilities.KeyTimer;
import resources.ListFixedLengthWithPredicate;
import resources.Output;

import java.util.ArrayList;

import static resources.Consts.*;

public class CheckAverages implements Runnable{

    private static Output processList(ListFixedLengthWithPredicate<Double> list, int seconds_short, int seconds_long){
        boolean ifChar = KeyTimer.getKeyTimer().checkIfShow(list, seconds_long);
        list.add(KeyTimer.getKeyTimer().getKeyPerTime(seconds_long, list.predicate));
        String StatsChar = StatsString(KeyTimer.getKeyTimer().getKeyPerTime(seconds_long,list.predicate),
                KeyTimer.getKeyTimer().getKeyPerTime(seconds_short,list.predicate ), list.name);
        return new Output(StatsChar, ifChar);
    }

    public static Output processData(ArrayList<ListFixedLengthWithPredicate> ListOfLists,int seconds_short, int seconds_long ){
        ArrayList<Output> Result = new ArrayList<Output>();
        for( ListFixedLengthWithPredicate<Double> lista : KeyTimer.getKeyTimer().ListOfListOfAverages ){
            Result.add( processList( lista, seconds_short, seconds_long) );
        }
        boolean ifShow = false;
        String Stats = "FORM: ";
        for(Output o : Result){
            if(o.ifShow == true) ifShow = true;
            Stats += o.Statistics;
        }
        Stats += "\n";
        return new Output( Stats, ifShow);
    }

    @Override public void run(){
        Output Result = processData(KeyTimer.getKeyTimer().ListOfListOfAverages,
                                    AVERAGES_SHORT_STATS_TIME, AVERAGES_COLLECTING_TIME);
        boolean ifShow = Result.ifShow;
        String Stats = Result.Statistics;
        SimpleLogger.getLogger().writeToFile(Stats);
        System.out.print(Stats);
        if(ifShow)KeyTimer.getKeyTimer().showDialogBox(Stats);
    }
}
