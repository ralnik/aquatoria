package ru.ralnik.aquatoria.animation;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import ru.ralnik.aquatoria.R;

public class ButtonScale implements View.OnTouchListener {
    Activity activity;

    public ButtonScale(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                final Animation animScale = AnimationUtils.loadAnimation(activity, R.anim.scale);
                view.startAnimation(animScale);
                break;
            case MotionEvent.ACTION_MOVE: // движение
//                        title.setVisibility(View.VISIBLE);
//                        text.setText("Движение");
                break;
            case MotionEvent.ACTION_UP: // отпускание
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return false;
    }
}

