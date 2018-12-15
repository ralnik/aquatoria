package ru.ralnik.aquatoria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import ru.ralnik.aquatoria.animation.ButtonScale;

public class MainActivity extends AppCompatActivity {

    private ImageView btnMenuSlideIn, btnMenuSlideOut
            ;
    private ImageView bg_menu;
    private FrameLayout menuLayout;
    Animation slide_in, slide_out;

    private ImageView btn1, btn2, btn3, btn4, btn5, btn6, btn7;


    int height_menu;
    String myLog = "myDebug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initObjects();
    }

    private void initObjects() {
        btnMenuSlideIn = (ImageView) findViewById(R.id.btnMenuSlideIn);
        btnMenuSlideOut = (ImageView) findViewById(R.id.btnMenuSlideOut);
        bg_menu = (ImageView) findViewById(R.id.bg_menu);
        height_menu = bg_menu.getLayoutParams().height;
        menuLayout = (FrameLayout) findViewById(R.id.menuLayout);
        menuLayout.setVisibility(View.GONE);

        btn1 = (ImageView) findViewById(R.id.btn1);
        btn2 = (ImageView) findViewById(R.id.btn2);
        btn3 = (ImageView) findViewById(R.id.btn3);
        btn4 = (ImageView) findViewById(R.id.btn4);
        btn5 = (ImageView) findViewById(R.id.btn5);
        btn6 = (ImageView) findViewById(R.id.btn6);
        btn7 = (ImageView) findViewById(R.id.btn7);

        btn1.setOnTouchListener(new ButtonScale(this));
        btn2.setOnTouchListener(new ButtonScale(this));
        btn3.setOnTouchListener(new ButtonScale(this));
        btn4.setOnTouchListener(new ButtonScale(this));
        btn5.setOnTouchListener(new ButtonScale(this));
        btn6.setOnTouchListener(new ButtonScale(this));
        btn7.setOnTouchListener(new ButtonScale(this));
    }

    public void btnMenuOnClick(View v){
        slide_in = AnimationUtils.loadAnimation(this, R.anim.anim_in);
        slide_out = AnimationUtils.loadAnimation(this, R.anim.anim_out);

        switch (v.getId()){
            case R.id.btnMenuSlideIn:
                 menuLayout.setVisibility(View.VISIBLE);
                 menuLayout.startAnimation(slide_in);
                 btnMenuSlideIn.setVisibility(View.INVISIBLE);
               break;

            case R.id.btnMenuSlideOut:
                menuLayout.startAnimation(slide_out);
                menuLayout.setVisibility(View.GONE);
                btnMenuSlideIn.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void buttonsSearchOnClick(View view){

    }

}
