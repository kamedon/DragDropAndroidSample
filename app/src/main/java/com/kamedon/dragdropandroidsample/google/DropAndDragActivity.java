package com.kamedon.dragdropandroidsample.google;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.kamedon.dragdropandroidsample.R;
import com.kamedon.dragdropandroidsample.custom.DragItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class DropAndDragActivity extends AppCompatActivity {

    private GridView mDragGridView;
    private DragItemAdapter mDragItemAdapter;
    private FrameLayout mDragDropView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_and_drag);
        List<Integer> icons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            icons.add(R.drawable.ic_launcher);
        }

        mDragDropView = (FrameLayout) findViewById(R.id.dragDropLayout);
        mDragGridView = (GridView) findViewById(R.id.fromLayout);
        mDragItemAdapter = new DragItemAdapter(getApplicationContext(), icons);
        mDragGridView.setAdapter(mDragItemAdapter);
        mDragGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData.Item item = new ClipData.Item("data_text");
                ClipDescription description = new ClipDescription("label", new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN});
                ClipData dragData = new ClipData(description, item);

                IconDragShadowBuilder shadow = new IconDragShadowBuilder(view);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(dragData, shadow, view, 0);
                } else {
                    view.startDrag(dragData, shadow, view, 0);
                }
                return true;
            }
        });
        mDragDropView.setOnDragListener(new IconDragEventListener(getApplicationContext()));
    }
}
