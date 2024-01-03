package com.example.wanderer;

import android.content.Context;

import java.io.File;

public class JsonDB {
    static String gradovi_folder;
    static String JsonDB_path;
    static String gradovi = "gradovi.json";


    public JsonDB() {
        this.JsonDB_path ="/data/user/0/com.example.wanderer/files/";
        this.gradovi_folder = "gradovi/";
    }
    public  JsonDB(Context context) {
        this.JsonDB_path = String.valueOf(context.getFilesDir());
        this.gradovi_folder = "gradovi/";
    }

    public  JsonDB(Context context, String gradovi_folder) {
        this.JsonDB_path = String.valueOf(context.getFilesDir());
        this.gradovi_folder = gradovi_folder;
    }

    public static String getGradovi_folder() {
        return gradovi_folder;
    }
    public static String getGradovi_folder_absolute() {
        return JsonDB_path+gradovi_folder;
    }

    public static void createFolderInInternalStorage_and_populate(Context context) {
        File db_internal = context.getFilesDir();
        File db_gradovi = new File(db_internal, getGradovi_folder());

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

        // Check if gradovi.java exists
        if (!new File(db_gradovi.getAbsolutePath()+gradovi).exists()) {

        }
    }

}
