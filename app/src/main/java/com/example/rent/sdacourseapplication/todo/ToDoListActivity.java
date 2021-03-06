package com.example.rent.sdacourseapplication.todo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rent.sdacourseapplication.R;
import com.example.rent.sdacourseapplication.drawing.DrawingActivity;


public class ToDoListActivity extends AppCompatActivity implements OnItemCheckStateChanged {

    private TodoListAdapter todoListAdapter;
    private String activityTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);
        activityTitle = getSupportActionBar().getTitle().toString();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        todoListAdapter = new TodoListAdapter();
        recyclerView.setAdapter(todoListAdapter);
        todoListAdapter.setCheckListener(this);

        final EditText editText = (EditText) findViewById(R.id.todo_edit_text) ;

        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoListAdapter.addItem((editText.getText().toString()));
            }
        });

    }

    @Override
    public void onItemCheckStateChanged(int checkedItemsCount) {

        if(checkedItemsCount >0 ) {
            getSupportActionBar().setTitle("Checked items" + checkedItemsCount);
        } else {
            getSupportActionBar().setTitle(activityTitle);

        }




    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_item) {
            todoListAdapter.deleteAllCheckedItems();
        }
        return super.onContextItemSelected(item);



    }



}
