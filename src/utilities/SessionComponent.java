package utilities;

import com.intellij.openapi.components.ProjectComponent;
import resources.Consts;

public class SessionComponent implements ProjectComponent {
    public void projectClosed() {
        SimpleLogger.getLogger().writeToFile(Consts.END_OF_LOG);
        SimpleLogger.getLogger().backUp();
    }
}
