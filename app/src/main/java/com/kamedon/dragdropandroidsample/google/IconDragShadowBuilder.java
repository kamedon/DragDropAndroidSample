package com.kamedon.dragdropandroidsample.google;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

/**
 * Created by kamei.hidetoshi on 2017/01/15.
 */

public class IconDragShadowBuilder extends View.DragShadowBuilder {

    public IconDragShadowBuilder(View v) {
        super(v);
    }


    // Defines a callback that sends the drag shadow dimensions and touch point back to the
    // system.
    @Override
    public void onProvideShadowMetrics(Point size, Point touch) {
        size.set(getView().getWidth(), getView().getHeight());
        touch.set(getView().getWidth()/ 2, getView().getHeight() * 2);
    }

    // Defines a callback that draws the drag shadow in a Canvas that the system constructs
    // from the dimensions passed in onProvideShadowMetrics().
    @Override
    public void onDrawShadow(Canvas canvas) {
        getView().draw(canvas);
    }
}
