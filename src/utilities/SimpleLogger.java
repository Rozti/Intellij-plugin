package utilities;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import resources.Consts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleLogger {
    private static SimpleLogger logger;
    private LocalFileSystem localFileSystem = LocalFileSystem.getInstance();
    private Path directoryPath;
    private VirtualFile dic;
    private String fileName;
    private String lastBackupName;
    private String userNameFileName;

    private SimpleLogger(String fileName, String path){
        this.fileName = fileName;
        this.userNameFileName = "USER.txt";
        try {
            directoryPath = Files.createTempDirectory(path);
            this.dic = localFileSystem.findFileByIoFile(this.directoryPath.toFile());
        } catch (IOException e) {
            writeToFile("Cannot create new temporary directory, extension won't work.");
        }
        BackUpLogTask.setTask();
    }

    public static SimpleLogger getLogger(){
        if (logger == null) {
            logger = new SimpleLogger(Consts.LOG_FILE_NAME,Consts.LOG_FILE_DIR);
            logger.getId();
        }
        return logger;
    }

    public synchronized void writeToFile(String newContent){
        VirtualFile file = null;
        try {
            file = dic.findOrCreateChildData(dic,fileName);
            byte[] content = file.contentsToByteArray();
            String contentString = new String(content);

            StringBuilder sa = new StringBuilder();
            if(!contentString.isEmpty())
                sa.append(contentString + "\n");
            sa.append(newContent);
            byte[] data = sa.toString().getBytes();
            file.setBinaryContent(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void deleteFile(String fileName){
        try {
            VirtualFile file = dic.findOrCreateChildData(dic,fileName);
            file.delete(ApplicationManager.getApplication());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getId(){
        VirtualFile file = null;

        try {
            file = dic.findOrCreateChildData(dic,userNameFileName);
            //System.out.print(file);


            byte[] fileContent = (new String("qwerty")).getBytes();
            if(file.getLength()==0) fileContent = (new String("getLength==0")).getBytes();
            file.setBinaryContent(fileContent);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void backUp(){
        VirtualFile file = null;
        VirtualFile backUp = null;
        try {
            file = dic.findOrCreateChildData(dic,fileName);
            byte[] content = file.contentsToByteArray();
            String contentString = new String(content);

            if(!contentString.isEmpty() && !contentString.equals(Consts.END_OF_LOG)) {
                byte[] clearFile = (new String()).getBytes();
                file.setBinaryContent(clearFile);

                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss");
                Date date = new Date();
                lastBackupName = fileName + dateFormat.format((date)) + ".txt";
                String serverFileName = Consts.USER_ID + dateFormat.format((date));

                ConnectionManager.sendPostRequest(contentString, serverFileName);

                backUp = dic.findOrCreateChildData(dic, lastBackupName);
                backUp.setBinaryContent(content);
                //deleteFile(lastBackupName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendToSerwer(){
        VirtualFile backUp = null;
        try {
            String path = directoryPath + "\\" +lastBackupName;
            File file = new File(path);
            backUp = localFileSystem.findFileByIoFile(file);
            if(backUp != null) {
                byte[] content = backUp.contentsToByteArray();
                String contentString = new String(content);

                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss");
                Date date = new Date();
                String serverFileName = Consts.USER_ID + dateFormat.format((date));

                ConnectionManager.sendPostRequest(contentString,serverFileName);
                deleteFile(lastBackupName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
