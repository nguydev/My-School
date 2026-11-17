package com.edupulse.app.ui.common;

import android.app.Activity;
import android.content.Context;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;

public class ViewAnimationUtils {

    /**
     * Tactile bounce and micro-interaction hover/press with haptics
     * Preserves onClick event by returning false and handling cancel/exit states
     */
    public static void applyBounceEffect(View view) {
        if (view == null) return;
        
        view.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
                    v.animate()
                            .scaleX(0.94f)
                            .scaleY(0.94f)
                            .alpha(0.88f)
                            .setDuration(90)
                            .start();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    v.animate()
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .alpha(1.0f)
                            .setDuration(150)
                            .setInterpolator(new OvershootInterpolator(2.0f))
                            .start();
                    break;
            }
            return false;
        });
    }

    /**
     * Batch applies bounce micro-interaction to multiple views
     */
    public static void applyBounceEffect(View... views) {
        for (View v : views) {
            applyBounceEffect(v);
        }
    }

    /**
     * Auto hides software keyboard when clicking outside input fields
     */
    public static void hideKeyboardOnTouchOutside(@NonNull View view, @NonNull Activity activity) {
        if (view == null || activity == null) return;
        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                hideKeyboard(activity);
            }
            return false;
        });
    }

    /**
     * Hides the active soft keyboard
     */
    public static void hideKeyboard(@NonNull Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
    }
}
