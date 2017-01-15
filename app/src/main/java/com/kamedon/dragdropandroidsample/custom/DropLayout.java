package com.kamedon.dragdropandroidsample.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kamedon.dragdropandroidsample.R;

/**
 * Created by h_kamei on 2015/11/18.
 */
public class DropLayout extends FrameLayout implements DragDropLayout.DropInterface {
    private final Paint mPaint = new Paint();
    private float mHoverX;
    private float mHoverY;
    private boolean isHover = false;
    private View mView;

    public DropLayout(Context context) {
        super(context);
    }

    public DropLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mView = findViewById(R.id.view);
    }

    @Override
    public boolean hit(float x, float y) {
        return (getRight() >= x && getBottom() >= y);
    }

    @Override
    public void hover(float x, float y, DragItem item) {
        mView.setVisibility(VISIBLE);
        mView.setX(x - mView.getWidth() / 2);
        mView.setY(y - mView.getHeight() / 2);
    }

    @Override
    public void drop(float x, float y, DragItem item) {
        mView.setVisibility(INVISIBLE);
        ImageView image = new ImageView(getContext());
        image.setImageResource(item.resource);
        addView(image);
        Drawable drawable = ContextCompat.getDrawable(getContext(), item.resource);
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        image.getLayoutParams().width = w;
        image.getLayoutParams().height = h;
        image.setX(x - w / 2);
        image.setY(y - h / 2);
    }
}
