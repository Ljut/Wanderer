package com.example.wanderer;

import android.os.AsyncTask;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloadTask extends AsyncTask<String, Void, Boolean> {

    private DownloadListener downloadListener;

    public interface DownloadListener {
        void onDownloadComplete(boolean success);
    }

    public FileDownloadTask(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String fileUrl = params[0];
        String destinationPath = params[1];

        try {
            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Check the HTTP response code
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();

                // Save the file to the local storage
                try (FileOutputStream outputStream = new FileOutputStream(destinationPath)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                return true;
            } else {
                // Handle the error, e.g., show a toast or log the error message
                System.out.println("Failed to download file. HTTP response code: " + urlConnection.getResponseCode());
            }

            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, e.g., show a toast or log the error message
            System.out.println("Error downloading file: " + e.getMessage());
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (downloadListener != null) {
            downloadListener.onDownloadComplete(success);
        }
    }
}