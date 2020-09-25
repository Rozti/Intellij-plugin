package utilities;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import resources.*;
import javax.swing.*;
import java.awt.*;

import static resources.Consts.*;



public class EmotionsForm extends AnAction {

    public static class MyPanel extends JPanel{

        public JList<String> countryList;
        public MyPanel(){
            setLayout(new GridLayout(2,1));
            add(new Label ("Jak się czujesz?"));
            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement("Dobrze");
            listModel.addElement("Smutny");
            listModel.addElement("Zły");
            countryList = new JList<>(listModel);
            add(countryList);
        }
        public void OpenDialogWindow(String Stats){
            JOptionPane.showMessageDialog(this, this
                    , "Pytanie o nastrój", JOptionPane.PLAIN_MESSAGE,null);
            String s = ((MyPanel)this).countryList.getSelectedValue();
            if ((s != null) && (s.length() > 0)) {
                SimpleLogger.getLogger().writeToFile("FORM ANSWER: " + s + " " + Stats);
                System.out.print("FORM ANSWER: " + s  +" "+ Stats);
                return;
            }
        }
    }


    public EmotionsForm() {
    }

    public void actionPerformed(AnActionEvent event) {
        Output Result = CheckAverages.processData(KeyTimer.getKeyTimer().ListOfListOfAverages,
                                            AVERAGES_SHORT_STATS_TIME, AVERAGES_COLLECTING_TIME);
        String Stats = Result.Statistics;
        KeyTimer.getKeyTimer().showDialogBox(Stats);

    }

    @Override
    public void update(AnActionEvent anActionEvent) {

        Project project = anActionEvent.getProject();
        anActionEvent.getPresentation().setEnabledAndVisible(project != null);
    }
}