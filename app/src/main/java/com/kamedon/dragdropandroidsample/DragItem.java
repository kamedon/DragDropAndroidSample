package com.kamedon.dragdropandroidsample;

import android.graphics.drawable.Drawable;

/**
 * Created by h_kamei on 2015/11/18.
 */
public class DragItem {

    public final Drawable previewDrawable;
    public final int resource;

    public DragItem(Drawable drawable, int resource) {
        previewDrawable = drawable;
        this.resource = resource;
    }
}
