import com.intellij.ide.IdeEventQueue;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import resources.Consts;
import utilities.KeyTimer;
import utilities.KeyPressedTimer;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import utilities.SimpleLogger;
import java.awt.event.KeyEvent;

public class KeyReading extends AnAction {

    static {
        Disposable myDisposable = Disposer.newDisposable();

        IdeEventQueue.getInstance().addDispatcher(e -> {
            if (e instanceof KeyEvent){
                if(e.getID() == KeyEvent.KEY_PRESSED) {
                    KeyPressedTimer.getKeyPressedTimer().setHoldTimer(((KeyEvent) e).getKeyCode());
                }
            }
            if (e instanceof KeyEvent){
                if(e.getID() == KeyEvent.KEY_RELEASED) {
                    long tempTime = 0;
                    long holdTime = 0;

                    if (KeyPressedTimer.getKeyPressedTimer().getStartTime() != -1)
                        tempTime = System.nanoTime() - KeyPressedTimer.getKeyPressedTimer().getStartTime();

                    if (KeyPressedTimer.getKeyPressedTimer().getHoldTime() != -1)
                        holdTime = System.nanoTime() - KeyPressedTimer.getKeyPressedTimer().getHoldTime();

                    SimpleLogger.getLogger().writeToFile(
                        Consts.typedKey((KeyEvent)e) + " diffTime: " + tempTime + " holdTime: " + holdTime);

                    KeyPressedTimer.getKeyPressedTimer().setTimer();
                    KeyPressedTimer.getKeyPressedTimer().resetHoldTimer();

                    //gromadzenie danych do statystyk
                    KeyTimer.getKeyTimer().addKey(System.nanoTime(), Consts.typedKey((KeyEvent)e));
                }
            }
            return false;
        }, myDisposable);
    }

    @Override
    public void actionPerformed(final AnActionEvent e) {
    }

    @Override
    public void update(final AnActionEvent e) {
    }
}