package com.kamedon.dragdropandroidsample.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.kamedon.dragdropandroidsample.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DragItemAdapter mDragItemAdapter;
    private GridView mDragGridView;
    private DragDropLayout mDragDropLayout;
    private DropLayout mDrogLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Integer> icons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            icons.add(R.drawable.ic_launcher);
        }

        mDragDropLayout= (DragDropLayout) findViewById(R.id.dragDropLayout);
        mDrogLayout = (DropLayout) findViewById(R.id.toLayout);
        mDragDropLayout.setDropLayout(mDrogLayout);
        mDragGridView = (GridView) findViewById(R.id.fromLayout);
        mDragItemAdapter = new DragItemAdapter(getApplicationContext(), icons);
        mDragGridView.setAdapter(mDragItemAdapter);
        mDragGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView image = (ImageView) view;
                Integer iconResource = mDragItemAdapter.getItem(position);
                mDragDropLayout.drag(new DragItem(image.getDrawable(),iconResource));
                return true;
            }
        });
    }


}
