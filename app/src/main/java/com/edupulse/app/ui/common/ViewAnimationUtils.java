package com.edupulse.app.ui.common;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.edupulse.app.R;

public class ViewAnimationUtils {

    /**
     * Attaches tactile bounce/scale spring animation on touch/click
     */
    public static void applyBounceEffect(View view) {
        if (view == null) return;
        
        view.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(120).start();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(120).start();
                    break;
            }
            return false;
        });
    }

    /**
     * Batch applies bounce effect to multiple views
     */
    public static void applyBounceEffect(View... views) {
        for (View v : views) {
            applyBounceEffect(v);
        }
    }
}
