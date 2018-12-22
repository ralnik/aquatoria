package ru.ralnik.aquatoria.XLS;

import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CopyXlsFromUrl extends Thread {
    private final String src;
    private final File dest;

    public CopyXlsFromUrl(String src, File dest) {
        this.src = src;
        this.dest = dest;
    }

    @Override
    public void run() {
        try {
            FileUtils.copyURLToFile(new URL(src), dest);
            onDownloadComplete(true);
        } catch (IOException e) {
            e.printStackTrace();
            onDownloadComplete(false);
        }
    }

    private void onDownloadComplete(boolean success) {
        // файл скачался, можно как-то реагировать
        Log.d("myDebug", "закачка завершена - " + success);
    }
}