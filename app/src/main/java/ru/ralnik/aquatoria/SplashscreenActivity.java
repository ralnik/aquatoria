package ru.ralnik.aquatoria;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import ru.ralnik.aquatoria.XLS.CopyXlsFromUrl;
import ru.ralnik.aquatoria.permissions.MyPermissions;

public class SplashscreenActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        this.context = this;


         new MyPermissions(this,this).verifyStoragePermissions();
              //  copyXLS();
              //  new Parser(context).run();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        startApp();
                        finish();
                    }
                }).start();
    }

    private void copyXLS(){
       new CopyXlsFromUrl(GlobalVars.XLS_URL, new File(GlobalVars.XLS_FILE)).start();
    }

    private void startApp() {
        Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
