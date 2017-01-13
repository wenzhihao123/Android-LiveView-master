package cm.wzh.live.view;

import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cm.wzh.live.R;

/**
 * Created by WZH on 2016/12/25.
 */

public class SliderView extends RelativeLayout implements View.OnTouchListener,
        GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;
    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;
    private LayoutTransition transition ;
    public SliderView(Context context) {
        this(context,null);
    }

    public SliderView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SliderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mGestureDetector = new GestureDetector(this);
        setOnTouchListener(this);
        setLongClickable(true);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX()-e2.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            // Fling left
//            Toast.makeText(getContext(), "向左手势", Toast.LENGTH_SHORT).show();
            for (int i=0;i<getChildCount();i++){
                getChildAt(i).setVisibility(VISIBLE);
                getChildAt(i).setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
            }
//            transition.setAnimator(LayoutTransition.APPEARING, AnimatorInflater.loadAnimator(getContext(), R.animator.animator_scale));
//            setLayoutTransition(transition);
        } else if (e2.getX()-e1.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            // Fling right
            for (int i=0;i<getChildCount();i++){
                if (getChildAt(i).getId()!=R.id.liveId){
                    getChildAt(i).setVisibility(GONE);
                }else {
                    getChildAt(i).setVisibility(VISIBLE);
                }
            }
//            transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,transition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));
//            setLayoutTransition(transition);
//            Toast.makeText(getContext(), "向右手势", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
