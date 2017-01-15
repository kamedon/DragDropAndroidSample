package com.kamedon.dragdropandroidsample.google;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.kamedon.dragdropandroidsample.R;

/**
 * Created by kamei.hidetoshi on 2017/01/15.
 */

public class IconDragEventListener implements View.OnDragListener {
    private final Context context;
    private float pX;
    private float pY;

    public IconDragEventListener(Context context) {
        this.context = context;
    }

    public boolean onDrag(View v, DragEvent event) {

        final int action = event.getAction();

        // Handles each of the expected events
        switch (action) {

            case DragEvent.ACTION_DRAG_STARTED:

                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                    v.setBackgroundColor(Color.BLUE);
                    v.invalidate();
                    return true;

                }

                return false;

            case DragEvent.ACTION_DRAG_ENTERED:

                v.setBackgroundColor(Color.GREEN);
                v.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                pX = event.getX();
                pY = event.getY();
                Log.d("test", event.getX() + "/" + event.getY());
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                v.setBackgroundColor(Color.BLUE);
                v.invalidate();

                return true;

            case DragEvent.ACTION_DROP:

                ClipData.Item item = event.getClipData().getItemAt(0);
                CharSequence dragData = item.getText();
                Toast.makeText(context, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
                v.setBackgroundColor(Color.WHITE);
                v.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                v.setBackgroundColor(Color.WHITE);
                v.invalidate();
                if (event.getResult()) {
                    Toast.makeText(context, "The drop was handled.", Toast.LENGTH_SHORT).show();
                    if (v instanceof ViewGroup) {
                        ViewGroup layout = (ViewGroup) v;
                        ImageView imageView = new ImageView(context);
                        ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        imageView.setLayoutParams(p);
                        imageView.setX(pX);
                        imageView.setY(pY);
                        imageView.setImageResource(R.drawable.ic_launcher);
                        layout.addView(imageView);
                    }
                } else {
                    Toast.makeText(context, "The drop didn't work.", Toast.LENGTH_SHORT).show();
                }
                return true;

            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }

        return false;
    }
}
