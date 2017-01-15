package com.kamedon.dragdropandroidsample.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kamedon.dragdropandroidsample.R;

/**
 * Created by h_kamei on 2015/11/18.
 */
public class DragDropLayout extends RelativeLayout implements View.OnTouchListener {
    private ImageView mImage;
    private DragItem mItem;
    private DropInterface mDrapLayout;
    private float mOffsetX;
    private float mOffsetY;


    public DragDropLayout(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public DragDropLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public void setDropLayout(DropInterface layout) {
        mDrapLayout = layout;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mItem != null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImage = (ImageView) findViewById(R.id.previewDragImage);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float pX, pY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mOffsetX = mImage.getWidth() / 2f;
                mOffsetY = mImage.getHeight() * 3 / 2f;
                mImage.setX(x - mOffsetX);
                mImage.setY(y - mOffsetY);
                mImage.setVisibility(VISIBLE);
                pX = mImage.getX() + mImage.getWidth() / 2;
                pY = mImage.getY() + mImage.getHeight() / 2;
                if (mDrapLayout.hit(pX, pY)) {
                    mDrapLayout.hover(pX, pY, mItem);
                }
                break;
            case MotionEvent.ACTION_UP:
                mOffsetX = mImage.getWidth() / 2f;
                mOffsetY = mImage.getHeight() * 3 / 2f;
                mImage.setX(x - mOffsetX);
                mImage.setY(y - mOffsetY);
                pX = mImage.getX() + mImage.getWidth() / 2;
                pY = mImage.getY() + mImage.getHeight() / 2;
                mDrapLayout.drop(pX, pY, mItem);
                mItem = null;
                mImage.setVisibility(INVISIBLE);
                mImage.setImageBitmap(null);
                break;
        }
        return false;
    }

    public void drag(DragItem item) {
        mItem = item;
        mImage.setImageDrawable(item.previewDrawable);
        mImage.getLayoutParams().width = item.previewDrawable.getIntrinsicWidth();
        mImage.getLayoutParams().height= item.previewDrawable.getIntrinsicHeight();
    }

    public interface DropInterface {
        boolean hit(float x, float y);

        void hover(float x, float y, DragItem item);

        void drop(float x, float y, DragItem item);
    }
}
