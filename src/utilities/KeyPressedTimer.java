package utilities;

public class KeyPressedTimer {
    private static KeyPressedTimer keyPressedTimer;
    private static long startTime = -1;
    private static long holdTime = -1;
    private static int holdKey = -1;

    private KeyPressedTimer(){}

    public static KeyPressedTimer getKeyPressedTimer(){
        if(keyPressedTimer == null){
            keyPressedTimer = new KeyPressedTimer();
        }
        return keyPressedTimer;
    }

    public long getStartTime(){
        return startTime;
    }
    public long getHoldTime(){
        return holdTime;
    }

    public void setTimer(){
        if(keyPressedTimer != null) {
            startTime = System.nanoTime();
        }
    }

    public void setHoldTimer(int typed){
        if(keyPressedTimer != null && typed != holdKey) {
            holdKey = typed;
            holdTime = System.nanoTime();
        }
    }

    public void resetHoldTimer(){
        holdKey = -1;
    }
}
