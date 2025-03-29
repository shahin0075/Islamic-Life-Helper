package com.itamadersomaj.islamiclifehelper.HijriCalender;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class OnSwipeTouchListener implements View.OnTouchListener {
    private final GestureDetector gestureDetector;

    public void onSwipeBottom() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {
    }

    public void onSwipeTop() {
    }

    public OnSwipeTouchListener(Context context) {
        this.gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override 
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.gestureDetector.onTouchEvent(motionEvent);
    }

    
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override 
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        private GestureListener() {
        }

        @Override 
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            try {
                float y = motionEvent2.getY() - motionEvent.getY();
                float x = motionEvent2.getX() - motionEvent.getX();
                if (Math.abs(x) > Math.abs(y)) {
                    if (Math.abs(x) <= 100.0f || Math.abs(f) <= 100.0f) {
                        return false;
                    }
                    if (x > 0.0f) {
                        OnSwipeTouchListener.this.onSwipeRight();
                    } else {
                        OnSwipeTouchListener.this.onSwipeLeft();
                    }
                } else if (Math.abs(y) <= 100.0f || Math.abs(f2) <= 100.0f) {
                    return false;
                } else {
                    if (y > 0.0f) {
                        OnSwipeTouchListener.this.onSwipeBottom();
                    } else {
                        OnSwipeTouchListener.this.onSwipeTop();
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
