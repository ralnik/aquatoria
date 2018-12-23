package ru.ralnik.aquatoria;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;

import ru.ralnik.aquatoria.XLS.CopyXlsFromUrl;
import ru.ralnik.aquatoria.XLS.Parser;

public class SplashscreenActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        this.context = this;
           Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                          copyXLS();
                          new Parser(context).run();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                startApp();
                                finish();
                            }
                        }).start();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

    }

    private void copyXLS(){
        if(new File(GlobalVars.XLS_PATH).exists() == false){
            new File(GlobalVars.XLS_PATH).mkdirs();
        }
       new CopyXlsFromUrl(GlobalVars.XLS_URL, new File(GlobalVars.XLS_PATH + GlobalVars.XLS_FILE)).start();
    }

    private void startApp() {
        Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
