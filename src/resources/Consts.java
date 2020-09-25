package resources;

import java.awt.event.KeyEvent;
import java.util.function.Predicate;

public class Consts {
    public static Predicate<String> isChar(String typeOfKey){
        return s -> s == typeOfKey;
    }
    public static Predicate<String> isMovement(){
        return s ->
            s == HOME_STRING ||
            s == END_STRING ||
            s == PAGE_UP_STRING ||
            s == PAGE_DOWN_STRING ||
            s == ARROW_R_STRING ||
            s == ARROW_L_STRING ||
            s == ARROW_U_STRING ||
            s == ARROW_D_STRING;
        }

    //log
    public final static String LOG_FILE_NAME = "log.txt";
    public final static String LOG_FILE_DIR = "INZ";
    public final static String END_OF_LOG = "END";

    //serwer
    public static String USER_ID = "tgyui";
    public final static String SERWER_ADDRESS = "http://153.19.52.108/~marchod1/";
    public final static int SERWER_PORT = 8080;

    //backUp
    //czas podany w millisekundach
    public final static long fHOW_OFTEN = 1000*60*5;
    public final static long fDELAY = 1000*60*5;

    //czas podany w sekundach
    public final static int AVERAGES_COLLECTING_TIME = 30;
    public final static int AVERAGES_SHORT_STATS_TIME = 10;
    public final static int FixedListSize = 5;

    public final static String StatsString(double KeyPerLongTime, double KeyPerShortTime, String Type) {

        return Type + " PER " + (double) (AVERAGES_COLLECTING_TIME)/60 + " MINUTES " + KeyPerLongTime + " "
                + Type + " PER  " + AVERAGES_SHORT_STATS_TIME  + " SECONDS " + KeyPerShortTime + " ";

    }

    public static final String SPACE_STRING = "SPACE";
    public static final String BACKSPACE_STRING = "BACKSPACE";
    public static final String ENTER_STRING = "ENTER";
    public static final String HOME_STRING = "HOME";
    public static final String END_STRING = "END";
    public static final String PAGE_UP_STRING = "PAGE_UP";
    public static final String PAGE_DOWN_STRING = "PAGE_DOWN";
    public static final String INSERT_STRING = "INSERT";
    public static final String DELETE_STRING = "DELETE";
    public static final String ARROW_R_STRING = "ARROW_R";
    public static final String ARROW_L_STRING = "ARROW_L";
    public static final String ARROW_U_STRING = "ARROW_U";
    public static final String ARROW_D_STRING = "ARROW_D";
    public static final String ALT_STRING = "ALT";
    public static final String SHIFT_STRING = "SHIFT";
    public static final String CAPS_STRING = "CAPS";
    public static final String NUM_STRING = "NUM";
    public static final String TAB_STRING = "TAB";
    public static final String ESC_STRING = "ESC";
    public static final String CTR_STRING = "CTR";
    public static final String CHAR_STRING = "CHAR";

    public static String typedKey(KeyEvent e){
        String result = new String();
        switch (e.getKeyCode()){
            case KeyEvent.VK_SPACE: result= SPACE_STRING;break;
            case KeyEvent.VK_BACK_SPACE: result= BACKSPACE_STRING;break;
            case KeyEvent.VK_ENTER: result= ENTER_STRING;break;
            case KeyEvent.VK_HOME: result= HOME_STRING;break;
            case KeyEvent.VK_END: result= END_STRING;break;
            case KeyEvent.VK_PAGE_UP: result= PAGE_UP_STRING;break;
            case KeyEvent.VK_PAGE_DOWN: result= PAGE_DOWN_STRING;break;
            case KeyEvent.VK_INSERT: result= INSERT_STRING;break;
            case KeyEvent.VK_DELETE: result= DELETE_STRING;break;
            case KeyEvent.VK_RIGHT: result= ARROW_R_STRING;break;
            case KeyEvent.VK_LEFT: result= ARROW_L_STRING;break;
            case KeyEvent.VK_UP: result= ARROW_U_STRING;break;
            case KeyEvent.VK_DOWN: result= ARROW_D_STRING;break;
            case KeyEvent.VK_ALT: result= ALT_STRING;break;
            case KeyEvent.VK_SHIFT: result= SHIFT_STRING;break;
            case KeyEvent.VK_CAPS_LOCK: result= CAPS_STRING;break;
            case KeyEvent.VK_NUM_LOCK: result= NUM_STRING;break;
            case KeyEvent.VK_TAB: result= TAB_STRING;break;
            case KeyEvent.VK_ESCAPE: result= ESC_STRING;break;
            case KeyEvent.VK_CONTROL: result= CTR_STRING;break;
            default:result= CHAR_STRING;
        }
        return result;
    }
}
