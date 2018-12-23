package ru.ralnik.aquatoria;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import java.util.ArrayList;
import java.util.List;

import ru.ralnik.aquatoria.config.myConfig;
import ru.ralnik.aquatoria.customListView.listviewItemSelected;
import ru.ralnik.aquatoria.customListView.myAdapter;
import ru.ralnik.aquatoria.httpPlayer.HttpPlayerFactory;
import ru.ralnik.aquatoria.httpPlayer.PlayerCommands;
import ru.ralnik.aquatoria.sqlitedb.FlatRepository;

public class MainActivity extends AppCompatActivity {

    //---------MAIN LAYOUT----------
    LinearLayout mainPanel;
    LinearLayout resultPanel;
    LinearLayout settingsPanel;

    //----------MENU BUTTONS-----------
    private ImageView btnMenuSlideIn;
    private ImageView btnMenuSlideOut;
    private AbsoluteLayout menuLayout;
    Animation slide_in, slide_out;
    private ImageView btnPlayPause;
    private ImageView btnOptions;
    private ImageView btnVolume;

    private ImageView btn1, btn2, btn3, btn4, btn5, btn6, btn7;

    //--------- builds --------------
    private ImageView btnBuild_c1, hintBuild_c1;
    private ImageView btnBuild_c2, hintBuild_c2;
    private ImageView btnBuild_b1, hintBuild_b1;
    private ImageView btnBuild_b2, hintBuild_b2;

    //---------- countRooms ----------------
    private ImageView btnRoom1;
    private ImageView btnRoom2;
    private ImageView btnRoom3;
    private ImageView btnRoom4;
    private ImageView btnRoom5;

    //--------- additional attributes -------
    private ImageView btnLoggia;
    private ImageView btnBalcon;
    private ImageView btnKladovaya;

    //---------Buttons filter---------
    private ImageView btnClear;
    private ImageView btnSearch;

    //--------Controll Settings--------
    private View viewSettingPanel;
    private SeekBar musicSeekBar;
    private SeekBar effectSeekBar;
    private EditText editWaitTime;
    private ImageView switcherTimer;
    private EditText editIP;
    private ImageView btnSave;

    //------- SEEKBAR-------------
    private CrystalRangeSeekbar seekbarFloor;
    private CrystalRangeSeekbar seekbarCost;
    private CrystalRangeSeekbar seekbarSquare;

    private EditText minFloorEdit;
    private EditText maxFloorEdit;

    private EditText minCostEdit;
    private EditText maxCostEdit;

    private EditText minSquareEdit;
    private EditText maxSquareEdit;

    //------title cost and budget
    private ImageView titleCost;
    private ImageView titleBudget;

    TextView textNoRow;
    ListView listview;
    ArrayList<Integer> ListClearFilter = new ArrayList<>();


    myConfig cfg;
    myTimer timer;
    private PlayerCommands vvvv;
    private WebView webView;
    String TAG = "myDebug";
    private String query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //initialize config
        cfg = new myConfig(this);
        cfg.setEmail("ralnik@mail.ru");
        webView = (WebView) findViewById(R.id.webView);
        GlobalVars.webView = webView;
        vvvv = HttpPlayerFactory.getInstance(this).getCommand();

        initObjects();
        initSettings();
    }

    private void initObjects() {
        mainPanel = (LinearLayout) findViewById(R.id.mainLayout);
        resultPanel = (LinearLayout) findViewById(R.id.resultPanel);
        settingsPanel = (LinearLayout) findViewById(R.id.settingsLayout);
        mainPanel.setVisibility(View.VISIBLE);
        resultPanel.setVisibility(View.GONE);
        settingsPanel.setVisibility(View.GONE);

        btnMenuSlideIn = (ImageView) findViewById(R.id.btnMenuSlideIn);
        btnMenuSlideOut = (ImageView) findViewById(R.id.btnMenuSlideOut);
        menuLayout = (AbsoluteLayout) findViewById(R.id.menuLayout);
        menuLayout.setVisibility(View.GONE);

        btn1 = (ImageView) findViewById(R.id.btn1);
        btn2 = (ImageView) findViewById(R.id.btn2);
        btn3 = (ImageView) findViewById(R.id.btn3);
        btn4 = (ImageView) findViewById(R.id.btn4);
        btn5 = (ImageView) findViewById(R.id.btn5);
        btn6 = (ImageView) findViewById(R.id.btn6);
        btn7 = (ImageView) findViewById(R.id.btn7);

//        btn1.setOnTouchListener(new ButtonScale(this));
//        btn2.setOnTouchListener(new ButtonScale(this));
//        btn3.setOnTouchListener(new ButtonScale(this));
//        btn4.setOnTouchListener(new ButtonScale(this));
//        btn5.setOnTouchListener(new ButtonScale(this));
//        btn6.setOnTouchListener(new ButtonScale(this));
//        btn7.setOnTouchListener(new ButtonScale(this));


        btnBuild_b1 = (ImageView) findViewById(R.id.btnBuild_b1);
        hintBuild_b1 = (ImageView) findViewById(R.id.hintBuild_b1);
        btnBuild_b2 = (ImageView) findViewById(R.id.btnBuild_b2);
        hintBuild_b2 = (ImageView) findViewById(R.id.hintBuild_b2);
        btnBuild_c1 = (ImageView) findViewById(R.id.btnBuild_c1);
        hintBuild_c1 = (ImageView) findViewById(R.id.hintBuild_c1);
        btnBuild_c2 = (ImageView) findViewById(R.id.btnBuild_c2);
        hintBuild_c2 = (ImageView) findViewById(R.id.hintBuild_c2);

        btnBuild_b1.setTag(0);
        btnBuild_b2.setTag(0);
        btnBuild_c1.setTag(0);
        btnBuild_c2.setTag(0);

        hintBuild_b1.setVisibility(View.VISIBLE);
        hintBuild_b2.setVisibility(View.VISIBLE);
        hintBuild_c1.setVisibility(View.VISIBLE);
        hintBuild_c2.setVisibility(View.VISIBLE);

        btnRoom1 = (ImageView) findViewById(R.id.btnRoom1);
        btnRoom2 = (ImageView) findViewById(R.id.btnRoom2);
        btnRoom3 = (ImageView) findViewById(R.id.btnRoom3);
        btnRoom4 = (ImageView) findViewById(R.id.btnRoom4);
        btnRoom5 = (ImageView) findViewById(R.id.btnRoom5);
        btnRoom1.setTag(0);
        btnRoom2.setTag(0);
        btnRoom3.setTag(0);
        btnRoom4.setTag(0);
        btnRoom5.setTag(0);

        btnLoggia = (ImageView) findViewById(R.id.btnLoggia);
        btnBalcon = (ImageView) findViewById(R.id.btnBalcon);
        btnKladovaya = (ImageView) findViewById(R.id.btnKladovaya);
        btnKladovaya.setTag(0);
        btnLoggia.setTag(0);
        btnBalcon.setTag(0);

        textNoRow = (TextView) findViewById(R.id.textNoRow);
        listview = (ListView) findViewById(R.id.listview);

        btnClear = (ImageView) findViewById(R.id.btnClear);
        btnSearch = (ImageView) findViewById(R.id.btnSearch);

        titleCost = (ImageView) findViewById(R.id.titleCost);
        titleBudget = (ImageView) findViewById(R.id.titleBudget);
        titleCost.setTag(1);
        titleBudget.setTag(0);
        titleBudget.setImageResource(R.drawable.title_budget);
        titleCost .setImageResource(R.drawable.title_cost_down);

        seekbarFloor  = (CrystalRangeSeekbar) findViewById(R.id.seekBarFloor);
        seekbarCost   = (CrystalRangeSeekbar) findViewById(R.id.seekBarCost);
        seekbarSquare = (CrystalRangeSeekbar) findViewById(R.id.seekBarSquare);
        minFloorEdit = (EditText) findViewById(R.id.minFloorEdit);
        maxFloorEdit = (EditText) findViewById(R.id.maxFloorEdit);;
        minCostEdit = (EditText) findViewById(R.id.minCostEdit);
        maxCostEdit = (EditText) findViewById(R.id.maxCostEdit);
        minSquareEdit = (EditText) findViewById(R.id.minSquareEdit);
        maxSquareEdit = (EditText) findViewById(R.id.maxSquareEdit);
        SeekbarSquareOnChange();
        SeekbarFloorOnChange();
        SeekbarCostOnChange();
        setValuesToSeekBar();

        btnPlayPause = (ImageView) findViewById(R.id.btnPlay);
        btnVolume = (ImageView) findViewById(R.id.btnSound);
        btnOptions = (ImageView) findViewById(R.id.btnOptions);
        btnPlayPause.setTag(0);
        btnVolume.setTag(1);
    }

    private void setValuesToSeekBar(){
//    try {
        cfg.setMinBudget(Float.valueOf(new FlatRepository(this).getMin("Budget").toString()));
        cfg.setMaxBudget(Float.valueOf(new FlatRepository(this).getMax("Budget").toString()));
        cfg.setMinCost(Float.valueOf(new FlatRepository(this).getMin("Cost").toString()));
        cfg.setMaxCost(Float.valueOf(new FlatRepository(this).getMax("Cost").toString()));
        cfg.setMinFloor((int) new FlatRepository(this).getMin("Floor"));
        cfg.setMaxFloor((int) new FlatRepository(this).getMax("Floor"));
        cfg.setMinSquare((float) new FlatRepository(this).getMin("Square"));
        cfg.setMaxSquare((float) new FlatRepository(this).getMax("Square"));
//    }catch (Exception e){
//        e.printStackTrace();
//    }

        setDataToCostSeekBar();

        seekbarFloor.setMinValue(cfg.getMinFloor());
        seekbarFloor.setMaxValue(cfg.getMaxFloor());
        minFloorEdit.setText(String.valueOf(cfg.getMinFloor()));
        maxFloorEdit.setText(String.valueOf(cfg.getMaxFloor()));

        seekbarSquare.setMinValue(cfg.getMinSquare());
        seekbarSquare.setMaxValue(cfg.getMaxSquare());
        minSquareEdit.setText(String.valueOf(cfg.getMinSquare()));
        maxSquareEdit.setText(String.valueOf(cfg.getMaxSquare()));

    }

    private void setDataToCostSeekBar() {
        float min = 0;
        float max = 0;
        if((Integer) titleCost.getTag() == 1) {
            seekbarCost.setMinValue(cfg.getMinCost());
            seekbarCost.setMaxValue(cfg.getMaxCost());
            min = cfg.getMinCost() / 1000;
            max = cfg.getMaxCost() / 1000;
        }
        if((Integer) titleBudget.getTag() == 1){
            seekbarCost.setMinValue(cfg.getMinBudget());
            seekbarCost.setMaxValue(cfg.getMaxBudget());
            min = cfg.getMinBudget() / 1000000;
            max = cfg.getMaxBudget() / 1000000;
        }

        //Log.d(TAG,"min="+min);
        String formattedDoubleMin = String.valueOf(min).substring(0,String.valueOf(min).indexOf(".")+2);
        String formattedDoubleMax = String.valueOf(max).substring(0,String.valueOf(max).indexOf(".")+2);
        //Log.d(TAG,"minSork="+formattedDouble);
        minCostEdit.setText(formattedDoubleMin.replace(",","."));
        maxCostEdit.setText(formattedDoubleMax.replace(",","."));
//        minCostEdit.setText(seekbarCost.getSelectedMinValue().toString());
//        maxCostEdit.setText(seekbarCost.getSelectedMaxValue().toString());
    }

    public void buttonsCaruselOnClick(View v){
        Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        v.startAnimation(animScale);
        switch (v.getId()){
            case R.id.btn1:
                v.startAnimation(animScale);
                vvvv.selectById(0);
                play();
                break;
            case R.id.btn2:
                v.startAnimation(animScale);
                vvvv.selectById(1);
                play();
                break;
            case R.id.btn3:
                v.startAnimation(animScale);
                vvvv.selectById(2);
                play();
                break;
            case R.id.btn4:
                v.startAnimation(animScale);
                vvvv.selectById(3);
                play();
                break;
            case R.id.btn5:
                v.startAnimation(animScale);
                vvvv.selectById(4);
                play();
                break;
            case R.id.btn6:
                v.startAnimation(animScale);
                vvvv.selectById(5);
                play();
                break;
            case R.id.btn7:
                v.startAnimation(animScale);
                vvvv.selectById(6);
                play();
                break;


        }
    }

    public void titleCost_BudgetOnClick(View v){
        switch (v.getId()){
            case R.id.titleCost:
                if((Integer) titleCost.getTag() == 0){
                    titleCost.setTag(1);
                    titleCost.setImageResource(R.drawable.title_cost_down);
                    titleBudget.setTag(0);
                    titleBudget.setImageResource(R.drawable.title_budget);
                    setDataToCostSeekBar();
                }
                break;
            case R.id.titleBudget:
                if((Integer) titleBudget.getTag() == 0){
                    titleBudget.setTag(1);
                    titleBudget.setImageResource(R.drawable.title_budget_down);
                    titleCost.setTag(0);
                    titleCost.setImageResource(R.drawable.title_cost);
                    setDataToCostSeekBar();
                }
                break;
        }
    }

    public void SeekbarSquareOnChange(){
        seekbarSquare.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {

                /*заменяем в строке запятую на точку, т.к. по неизвестным мне причинам
                на разных версиях андроид в EditView почему то тип float конвертируется с точкой а в каком то андроиде запятая.
                а нужно обязательно точка т.к. данные из этого Edit конвертируется обратно во float и передается другому activity
                и если стоит запятая конвертация не проходит, вываливается ошибка.
                */
                minSquareEdit.setText(String.format("%.2f", minValue).replace(",","."));
                maxSquareEdit.setText(String.format("%.2f", maxValue).replace(",","."));

            }
        });
        seekbarSquare.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                clearFilterActivate(true);
            }
        });
    }

    public void SeekbarCostOnChange(){
        seekbarCost.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                float min = 0;
                float max = 0;
                if((Integer) titleCost.getTag() == 1) {
                    min = Float.valueOf(minValue.toString()) / 1000;
                    max = Float.valueOf(maxValue.toString()) / 1000;
                }
                if((Integer) titleBudget.getTag() == 1){
                    min = Float.valueOf(minValue.toString()) / 1000000;
                    max = Float.valueOf(maxValue.toString()) / 1000000;
                }
                //Log.d(TAG,"min="+min);
                String formattedDoubleMin = String.valueOf(min).substring(0,String.valueOf(min).indexOf(".")+2);
                String formattedDoubleMax = String.valueOf(max).substring(0,String.valueOf(max).indexOf(".")+2);
                //Log.d(TAG,"minSork="+formattedDouble);
                minCostEdit.setText(formattedDoubleMin.replace(",","."));
                maxCostEdit.setText(formattedDoubleMax.replace(",","."));

            }
        });
        seekbarCost.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                clearFilterActivate(true);
            }
        });
    }

    public void SeekbarFloorOnChange(){
        seekbarFloor.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                minFloorEdit.setText(String.valueOf(minValue));
                maxFloorEdit.setText(String.valueOf(maxValue));
            }
        });
        seekbarFloor.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                clearFilterActivate(true);
            }
        });
    }

    public void btnBuildOnClick(View v){
        switch (v.getId()){
            case R.id.btnBuild_b1:
                if((Integer) btnBuild_b1.getTag() == 1){
                    btnBuild_b1.setTag(0);
                    btnBuild_b1.setImageResource(R.drawable.build_b1);
                }else{
                    btnBuild_b1.setTag(1);
                    btnBuild_b1.setImageResource(R.drawable.build_b1_down);
                }
                break;
            case R.id.btnBuild_b2:
                if((Integer) btnBuild_b2.getTag() == 1){
                    btnBuild_b2.setTag(0);
                    btnBuild_b2.setImageResource(R.drawable.build_b2);
                }else{
                    btnBuild_b2.setTag(1);
                    btnBuild_b2.setImageResource(R.drawable.build_b2_down);
                }
                break;
            case R.id.btnBuild_c1:
                if((Integer) btnBuild_c1.getTag() == 1){
                    btnBuild_c1.setTag(0);
                    btnBuild_c1.setImageResource(R.drawable.build_c1);
                }else{
                    btnBuild_c1.setTag(1);
                    btnBuild_c1.setImageResource(R.drawable.build_c1_down);
                }
                break;
            case R.id.btnBuild_c2:
                if((Integer) btnBuild_c2.getTag() == 1){
                    btnBuild_c2.setTag(0);
                    btnBuild_c2.setImageResource(R.drawable.build_c2);
                }else{
                    btnBuild_c2.setTag(1);
                    btnBuild_c2.setImageResource(R.drawable.build_c2_down);
                }
                break;
        }
    }

    public void btnRoomsOnClick(View v){
        switch (v.getId()){
            case R.id.btnRoom1:
                if((Integer) btnRoom1.getTag() == 1){
                    btnRoom1.setTag(0);
                    btnRoom1.setImageResource(R.drawable.btn_room1);
                    clearFilterActivate(false);
                } else{
                    btnRoom1.setTag(1);
                    btnRoom1.setImageResource(R.drawable.btn_room1_down);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnRoom2:
                if((Integer) btnRoom2.getTag() == 2){
                    btnRoom2.setTag(0);
                    btnRoom2.setImageResource(R.drawable.btn_room2);
                    clearFilterActivate(false);
                } else{
                    btnRoom2.setTag(2);
                    btnRoom2.setImageResource(R.drawable.btn_room2_down);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnRoom3:
                if((Integer) btnRoom3.getTag() == 3){
                    btnRoom3.setTag(0);
                    btnRoom3.setImageResource(R.drawable.btn_room3);
                    clearFilterActivate(false);
                } else{
                    btnRoom3.setTag(3);
                    btnRoom3.setImageResource(R.drawable.btn_room3_down);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnRoom4:
                if((Integer) btnRoom4.getTag() == 4){
                    btnRoom4.setTag(0);
                    btnRoom4.setImageResource(R.drawable.btn_room4);
                    clearFilterActivate(false);
                } else{
                    btnRoom4.setTag(4);
                    btnRoom4.setImageResource(R.drawable.btn_room4_down);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnRoom5:
                if((Integer) btnRoom5.getTag() == 5){
                    btnRoom5.setTag(0);
                    btnRoom5.setImageResource(R.drawable.btn_room5);
                    clearFilterActivate(false);
                } else{
                    btnRoom5.setTag(5);
                    btnRoom5.setImageResource(R.drawable.btn_room5_down);
                    clearFilterActivate(true);
                }
                break;
        }
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
            case R.id.btnPlay:
                if((Integer) btnPlayPause.getTag() == 1 ){
                    btnPlayPause.setImageResource(R.drawable.play);
                    btnPlayPause.setTag(0);
                    vvvv.stop();
                }else{
                    btnPlayPause.setImageResource(R.drawable.play_down);
                    btnPlayPause.setTag(1);
                    vvvv.play();
                }
                break;
            case R.id.btnSound:
                if((Integer) btnVolume.getTag() == 1 ){
                    btnVolume.setImageResource(R.drawable.button_sound_off);
                    btnVolume.setTag(0);
                    vvvv.volumeOnOff();
                }else{
                    btnVolume.setImageResource(R.drawable.button_sound_on);
                    btnVolume.setTag(1);
                    vvvv.volumeOnOff();
                }
                break;
            case R.id.btnOptions:
                mainPanel.setVisibility(View.GONE);
                resultPanel.setVisibility(View.GONE);
                settingsPanel.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void additionalAttriburesButtonsOnClick(View v){
        switch (v.getId()){
            case R.id.btnLoggia:
                if((Integer) btnLoggia.getTag() == 1){
                    btnLoggia.setTag(0);
                    btnLoggia.setImageResource(R.drawable.btn_loggia);
                    clearFilterActivate(false);
                } else{
                    btnLoggia.setTag(1);
                    btnLoggia.setImageResource(R.drawable.btn_loggia_down);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnBalcon:
                if((Integer) btnBalcon.getTag() == 1){
                    btnBalcon.setTag(0);
                    btnBalcon.setImageResource(R.drawable.btn_balcon);
                    clearFilterActivate(false);
                } else{
                    btnBalcon.setTag(1);
                    btnBalcon.setImageResource(R.drawable.btn_balcon_down);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnKladovaya:
                if((Integer) btnKladovaya.getTag() == 1){
                    btnKladovaya.setTag(0);
                    btnKladovaya.setImageResource(R.drawable.btn_kladovaya);
                    clearFilterActivate(false);
                } else{
                    btnKladovaya.setTag(1);
                    btnKladovaya.setImageResource(R.drawable.btn_kladovaya_down);
                    clearFilterActivate(true);
                }
                break;
        }
    }

    public void titleOfListviewOnClick(View v){
        String order = null;
        switch (v.getId()){
            case R.id.colKorpus:
                order = " order by category,sectionNumber";
                break;
            case R.id.colFlat:
                order = " order by BeforeBtiNumber";
                break;
            case R.id.colFloor:
                order = " order by Floor";
                break;
            case R.id.colRooms:
                order = " order by Rooms";
                break;
            case R.id.colSquare:
                order = " order by Quantity";
                break;
            case R.id.colCost:
                order = " order by DiscountMax";
                break;
            case R.id.colBalcon:
                order = " order by CountBalcony";
                break;
            case R.id.colLoggia:
                order = " order by CountLoggia";
                break;
            case R.id.colKladovaya:
                order = " order by Kladovaya";
                break;
        }
        loadFromSqlite(query + order);
    }


    public void buttonsSearchOnClick(View view){
        switch (view.getId()){
            case R.id.btnClear:
                clearFilter();
                break;
            case R.id.btnSearch:
                applyFilter();
                break;
        }
    }


    private void initSettings(){
        viewSettingPanel = this.getLayoutInflater().inflate(R.layout.activity_settings, null);
        musicSeekBar = (SeekBar) viewSettingPanel.findViewById(R.id.musicSeekBar);
        effectSeekBar = (SeekBar) viewSettingPanel.findViewById(R.id.effectSeekBar);
        editWaitTime = (EditText) viewSettingPanel.findViewById(R.id.editWaitTime);
        switcherTimer = (ImageView) viewSettingPanel.findViewById(R.id.switcherTimer);
        editIP = (EditText) viewSettingPanel.findViewById(R.id.editIP);
        btnSave = (ImageView) viewSettingPanel.findViewById(R.id.btnSave);
        settingsPanel.addView(viewSettingPanel);
        musicSeekBar.setProgress(cfg.getVolumeProgress());
        effectSeekBar.setProgress(cfg.getEffectProgress());
        editWaitTime.setText(String.valueOf(cfg.getTimer()));
        editIP.setText(cfg.getHost());

        if(cfg.getDisableTimer()) {
            switcherTimer.setTag("on");
            switcherTimer.setImageResource(R.drawable.on);
        }else {
            switcherTimer.setTag("off");
            switcherTimer.setImageResource(R.drawable.off);
        }

        musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Log.d(TAG,String.valueOf(musicSeekBar.getProgress()));
                //GlobalVar.volume = String.valueOf(VolumeSeekBar.getProgress());
                //GlobalVar.lastLink = VVVV.fullLink();
            }
        });

        effectSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG,String.valueOf(effectSeekBar.getProgress()));
                //GlobalVar.volEffects = String.valueOf(EffectSeekBar.getProgress());
                //GlobalVar.lastLink = VVVV.fullLink();
            }
        });
        switcherTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switcherTimer.getTag().equals("on")){
                    switcherTimer.setTag("off");
                    switcherTimer.setImageResource(R.drawable.off);
                }else{
                    switcherTimer.setTag("on");
                    switcherTimer.setImageResource(R.drawable.on);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
            }
        });
    }

    private void saveSettings() {
        if(!editIP.getText().toString().equals(cfg.getHost())) {
            showPasswordWindow();
        }

        //Log.d(TAG,"save config");
        if(switcherTimer.getTag().equals("on")) {
            cfg.setDisableTimer(true);
        }else{
            cfg.setDisableTimer(false);
        }

        cfg.setVolumeProgress(String.valueOf(musicSeekBar.getProgress()));
        cfg.setEffectProgress(String.valueOf(effectSeekBar.getProgress()));

        cfg.setTimer(String.valueOf(editWaitTime.getText()));
        settingsPanel.setVisibility(View.GONE);
        mainPanel.setVisibility(View.VISIBLE);
        setTimer(cfg.getTimer());

    }

    private void showPasswordWindow(){
        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompt, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                if(String.valueOf(userInput.getText()).equals("realred34")){
                                    cfg.setHost(editIP.getText().toString());
                                    vvvv.changeHost(cfg.getHost());
                                }else
                                {
                                    Toast toast2 = Toast.makeText(getApplicationContext(), "Неверный пароль!", Toast.LENGTH_LONG);
                                    toast2.setGravity(Gravity.CENTER, 0, 0);
                                    toast2.show();
                                }

                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();


    }


    private void applyFilter() {
        query = "select * from flats where ";

        query = query + " (floor >= "+seekbarFloor.getSelectedMinValue() + " and floor <= "+seekbarFloor.getSelectedMaxValue()+") ";
        query = query + " and (Square >= " + seekbarSquare.getSelectedMinValue() + " and Square <= "+((Float) seekbarSquare.getSelectedMaxValue()+0.1) + ") ";
        if((Integer) titleCost.getTag() == 1) {
            query = query + " and (CostForMetr >= " + minCostEdit.getText() + "*1000" + " and CostForMetr <= " + maxCostEdit.getText() + "*1000" + ") ";
        }
        if((Integer) titleBudget.getTag() == 1){
            query = query + " and (FullCost >= " + minCostEdit.getText() + "*1000000" + " and FullCost <= " + maxCostEdit.getText() + "*1000000" + ") ";
        }

        //Count ROOMS;
        List<Integer> countRoom = new ArrayList<>();
        if(Integer.valueOf(btnRoom1.getTag().toString()) > 0) { countRoom.add(Integer.valueOf(btnRoom1.getTag().toString())); }
        if(Integer.valueOf(btnRoom2.getTag().toString()) > 0) { countRoom.add(Integer.valueOf(btnRoom2.getTag().toString())); }
        if(Integer.valueOf(btnRoom3.getTag().toString()) > 0) { countRoom.add(Integer.valueOf(btnRoom3.getTag().toString())); }
        if(Integer.valueOf(btnRoom4.getTag().toString()) > 0) { countRoom.add(Integer.valueOf(btnRoom4.getTag().toString())); }
        if(Integer.valueOf(btnRoom5.getTag().toString()) > 0) { countRoom.add(Integer.valueOf(btnRoom5.getTag().toString())); }


        if(countRoom.size()>0){
            String countRoomString = "";
            for(int i = 0 ; i<countRoom.size();i++){
                if (countRoomString.length() == 0) {
                    countRoomString = countRoom.get(i).toString();
                } else {
                    countRoomString = countRoomString + "," + countRoom.get(i).toString();
                }
            }
            countRoomString = " and rooms in (" + countRoomString + ")";
            query = query + countRoomString;
        }

        //additional parameter
        if(Integer.valueOf(btnBalcon.getTag().toString())>0){query = query + " and CountBalcony = 1"; }
        if(Integer.valueOf(btnLoggia.getTag().toString())>0){query = query + " and CountLoggia = 1"; }
        if(Integer.valueOf(btnKladovaya.getTag().toString())>0){query = query + " and Kladovaya = 1"; }

        String buildQuery;
        List<String> countBuild = new ArrayList<>();
        if((Integer) btnBuild_b1.getTag() == 1 ){countBuild.add("B1");}
        if((Integer) btnBuild_b2.getTag() == 1 ){countBuild.add("B2");}
        if((Integer) btnBuild_c1.getTag() == 1 ){countBuild.add("C1");}
        if((Integer) btnBuild_c2.getTag() == 1 ){countBuild.add("C2");}


        if(countBuild.size()>0){
            String countBuildString = "";
            for(int i = 0 ; i<countBuild.size();i++){
                if (countBuildString.length() == 0) {
                    countBuildString = "'"+countBuild.get(i)+"'";
                } else {
                    countBuildString = countBuildString + ", '" + countBuild.get(i) + "'";
                }
            }
            countBuildString = " and Corpus in (" + countBuildString + ") ";
            query = query + countBuildString;
        }

        Log.d(TAG, query + " order by FullCost");

        loadFromSqlite(query + " order by FullCost");
    }

    private void clearFilter() {
        btnClear.setImageResource(R.drawable.btn_clear);
        ListClearFilter.clear();

        seekbarSquare.setMinStartValue(cfg.getMinSquare());
        seekbarSquare.setMaxStartValue(cfg.getMaxSquare());
        seekbarSquare.apply();

        seekbarCost.setMinStartValue(cfg.getMinCost());
        seekbarCost.setMaxStartValue(cfg.getMaxCost());
        seekbarCost.apply();

        seekbarFloor.setMinStartValue(cfg.getMinFloor());
        seekbarFloor.setMaxStartValue(cfg.getMaxFloor());
        seekbarFloor.apply();

        btnRoom1.setImageResource(R.drawable.btn_room1);
        btnRoom2.setImageResource(R.drawable.btn_room2);
        btnRoom3.setImageResource(R.drawable.btn_room3);
        btnRoom4.setImageResource(R.drawable.btn_room4);
        btnRoom5.setImageResource(R.drawable.btn_room5);
        btnRoom1.setTag(0);
        btnRoom2.setTag(0);
        btnRoom3.setTag(0);
        btnRoom4.setTag(0);
        btnRoom5.setTag(0);

        btnBuild_b1.setImageResource(R.drawable.build_b1);
        btnBuild_b2.setImageResource(R.drawable.build_b2);
        btnBuild_c1.setImageResource(R.drawable.build_c1);
        btnBuild_c2.setImageResource(R.drawable.build_c2);
        btnBuild_b1.setTag(0);
        btnBuild_b2.setTag(0);
        btnBuild_c1.setTag(0);
        btnBuild_c2.setTag(0);


        btnLoggia.setImageResource(R.drawable.btn_loggia);
        btnBalcon.setImageResource(R.drawable.btn_balcon);
        btnKladovaya.setImageResource(R.drawable.btn_kladovaya);
        btnLoggia.setTag(0);
        btnBalcon.setTag(0);
        btnKladovaya.setTag(0);
    }

    private void loadFromSqlite(String sql) {
        //Log.d(TAG, "mas_size" + new FlatRepository(getApplicationContext()).getAll().size());
        myAdapter adapter = new myAdapter(this, new FlatRepository(this).getFlatsByQuery(sql),0);
        //Log.d("myDebug","adapter.size="+adapter.getCount());
        if(adapter.getCount() == 0 ){
            textNoRow.setVisibility(View.VISIBLE);
        }else{
            textNoRow.setVisibility(View.GONE);
        }
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new listviewItemSelected(this));

        mainPanel.setVisibility(View.GONE);
        settingsPanel.setVisibility(View.GONE);
        resultPanel.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        if(settingsPanel.getVisibility() == View.VISIBLE || resultPanel.getVisibility() == View.VISIBLE) {
            if(settingsPanel.getVisibility() == View.VISIBLE){saveSettings();}
            mainPanel.setVisibility(View.VISIBLE);
            settingsPanel.setVisibility(View.GONE);
            resultPanel.setVisibility(View.GONE);
        }else {
            //если будет нажата кнопка назад на самом планшете, то программы свернется и не закроется
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }

    public void setTimer(int minute) {

        if (timer != null) {
            timer.cancel();
        }
        timer = new myTimer(this, minute * 60000, 1000);
        //номер трека в плейлисте на время простоя
        timer.setTimerTrack(7);
        if(cfg.getDisableTimer() == false){
            timer.start();
        }
    }

    private void clearFilterActivate(Boolean flag) {

        if (flag == true) {
            btnClear.setImageResource(R.drawable.btn_clear_down);
            //btnClear.setEnabled(flag);
            ListClearFilter.add(1);
            // Log.d("myDebug","count="+ListClearFilter.size());
        } else {
            int count = ListClearFilter.size();
            /*
                если кол-во = 1, значит всего одна кнопка была нажата, и при ее повторном нажатии можно кнопку фильтр делаеть не активной,
                также (или) если сам массив пустой, то также делать можно неактивной. перед этим методом обязательно нужно отчистить массив
                в тех местах где это необходимо.
                если кол-во > 1, и массив не пустой, а кто то послал запрос на то, чтобы сделать кнопку неактивной, то ничего не произойдет.
                такая ситуация может быть например если нажата кнопка 38 корпус, и следом 39. а потом пользователь подумал,а
                зачем мне 39, и отжал эту кнопку, а поскольку кнопка 38 еще нажата, и при проверка в массиве тоже будет больше 1,
                то кнопка не станет неактивной
            */

            if (count == 1 || count == 0) {
                btnClear.setImageResource(R.drawable.btn_clear);
                //btnClear.setEnabled(flag);
                ListClearFilter.clear();
            }
        }

    }

    private void play(){
        btnPlayPause.setImageResource(R.drawable.play_down);
        btnPlayPause.setTag(1);
        setTimer(cfg.getTimer());
    }
}
