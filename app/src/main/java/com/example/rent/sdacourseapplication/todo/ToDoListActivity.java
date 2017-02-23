package com.example.rent.sdacourseapplication.todo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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


public class ToDoListActivity extends AppCompatActivity {

    private TodoListAdapter todoListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        todoListAdapter = new TodoListAdapter();
        recyclerView.setAdapter(todoListAdapter);

        final EditText editText = (EditText) findViewById(R.id.todo_edit_text) ;

        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoListAdapter.addItem((editText.getText().toString()));
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_item) {
            todoListAdapter.deleteAllCheckedItems();
        }
        return super.onContextItemSelected(item);



    }



}
