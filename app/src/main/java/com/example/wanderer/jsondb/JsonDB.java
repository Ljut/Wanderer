package com.example.wanderer.jsondb;

import android.content.Context;

import com.example.wanderer.FileDownloadTask;

import java.io.File;

public class JsonDB {
    String gradovi_folder;
    String JsonDB_path;
    String gradovi = "gradovi.json";


    public JsonDB() {
        JsonDB_path ="/data/user/0/com.example.wanderer/files/";
        gradovi_folder = "gradovi/";
        //"/data/user/0/com.example.wanderer/files/";
    }



    public  JsonDB(Context context) {
        JsonDB_path = String.valueOf(context.getFilesDir());
        gradovi_folder = "gradovi/";
    }

    public  JsonDB(Context context, String gradovi_folder) {
        JsonDB_path = String.valueOf(context.getFilesDir());
        this.gradovi_folder = gradovi_folder;
    }

    public String getGradovi_folder() {
        return this.gradovi_folder;
    }
    public String getGradovi_folder_absolute() {
        return JsonDB_path+this.gradovi_folder;
    }

    public void createFolderInInternalStorage(Context context) {
        File db_internal = context.getFilesDir();
        File db_gradovi = new File(db_internal, this.getGradovi_folder());

        if (!db_gradovi.exists()) {
            boolean isDirectoryCreated = db_gradovi.mkdir();
            if (isDirectoryCreated) {
                // Directory created successfully
                System.out.println("Folder created: " + db_gradovi.getAbsolutePath());
            } else {
                // Failed to create directory
                System.out.println("Failed to create folder");
            }
        } else {
            // Directory already exists
            System.out.println("Folder already exists: " + db_gradovi.getAbsolutePath());
        }
        //System.out.println(db_gradovi.getAbsolutePath()+gradovi);
        // Check if gradovi.java exists
        /*if (!new File(db_gradovi.getAbsolutePath()+"/"+gradovi).exists()) {
            System.out.println(db_gradovi.getAbsolutePath()+"/"+gradovi);
            DownloadJson(context, gradovi);
        }*/
    }
    public static String jsonString = "";
    public static String getGradIfNotExists(Context context, String fileGrada) {

        if (!new File(context.getFilesDir().getAbsolutePath()+"/"+fileGrada).exists()) {
            System.out.println(context.getFilesDir().getAbsolutePath()+"/"+fileGrada);
            //DownloadJson(context, fileGrada);

            FileDownloadTask downloadTask = new FileDownloadTask(new FileDownloadTask.DownloadListener() {
                @Override
                public void onDownloadComplete(boolean success) {
                    if (success) {
                        // File downloaded successfully
                        // Perform any actions you need after the download is complete
                        System.out.println("USPIO DOWNLOADATI Sarajevo.json");
                        jsonString= JsonFileReader.readJsonFromAssets(context, context.getFilesDir().getAbsolutePath()+"/"+fileGrada);

                    } else {
                        // Handle the download failure
                        System.out.println("NISAM USPIO DOWNLOADATI Sarajevo.json");
                    }
                }
            });

            downloadTask.execute("https://raw.githubusercontent.com/Ljut/Gradovi_za_Wanderer/main/"+fileGrada, context.getFilesDir().getAbsolutePath()+"/"+fileGrada);

        } else {

        }
        return jsonString;
    }

    /*private static void DownloadJson(Context context, String file) {

        DownloadManager manager = (DownloadManager) getSystemService(context.DOWNLOAD_SERVICE);
    }*/




    //private static final String  = 'a';
}
