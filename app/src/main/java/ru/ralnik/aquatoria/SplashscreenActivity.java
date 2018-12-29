package ru.ralnik.aquatoria;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

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
                    .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        copyXLS();
                                        Thread.sleep(5000);
                                        new Parser(context).run();
                                        Thread.sleep(5000);
                                        startApp();
                                        finish();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();


                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

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
