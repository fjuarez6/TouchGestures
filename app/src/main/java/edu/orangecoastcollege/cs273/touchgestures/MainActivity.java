package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Define a gesture detector to listen to events on the ScrollView
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);
        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);
        // Hooking up the gesture detector to the ScrollView
        // 4 out of 5 gestures handled
        gestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
        // Special case: double tap
        gestureDetector.setOnDoubleTapListener(this);

    }

    // Any touch event is now dispatch from the activity to the ScrollView
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        singleTaps++;
        // Let's append to our gesture log
        gesturesLogTextView.append("\nonSingleTapConfirmed touch event");
        singleTapTextView.setText(String.valueOf(singleTaps));
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gesturesLogTextView.append("\nonDoubleTap()");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        doubleTaps++;
        gesturesLogTextView.append("\nonDoubleTapEvent");
        doubleTapTextView.setText(String.valueOf(doubleTaps));
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        gesturesLogTextView.append("\nonDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        gesturesLogTextView.append("\nonShowPress()");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        gesturesLogTextView.append("\nonSingleTapUp()");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        ++scrolls;
        gesturesLogTextView.append("\nonScroll: distanceX is " +
                 distanceX + ", distanceY is " + distanceY);
        scrollTextView.setText(String.valueOf(scrolls));
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        ++longPresses;
        gesturesLogTextView.append("\nonLongPress()");
        longPressTextView.setText(String.valueOf(longPresses));
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        ++flings;
        gesturesLogTextView.append("\nonFling: velocityX is " +
                velocityX + ", velocityY is " + velocityY);
        flingTextView.setText(String.valueOf(flings));
        return true;
    }
}
