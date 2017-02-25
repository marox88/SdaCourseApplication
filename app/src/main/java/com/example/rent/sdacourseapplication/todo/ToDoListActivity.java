package com.example.rent.sdacourseapplication.todo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.rent.sdacourseapplication.R;
import com.example.rent.sdacourseapplication.todo.OnItemCheckStateChanged;
import com.example.rent.sdacourseapplication.todo.TodoListAdapter;

import java.util.ArrayList;


public class ToDoListActivity extends AppCompatActivity implements OnItemCheckStateChanged {

    private static String ADAPTER_DATA;
    private TodoListAdapter todoListAdapter;
    private String acitivityTitle;
    private ActionMode actionMode;

    @Override
    public void onItemCheckStateChanged(int checkedItemsCount) {
        if (checkedItemsCount > 0) {
            createActionMode();
            actionMode.setTitle(getResources().getQuantityString(R.plurals.checked_items_plural, checkedItemsCount, checkedItemsCount));

        } else {
            if (actionMode != null) {
                actionMode.finish();
            }
            getSupportActionBar().setTitle(acitivityTitle);
        }
    }


    private void createActionMode() {
        actionMode = startSupportActionMode(new Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.todo_list_action_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    todoListAdapter.deleteAllCheckedItems();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelableArrayList(ADAPTER_DATA, new ArrayList<>(todoListAdapter.getItems()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        todoListAdapter.setItems(savedInstanceState.<TodoListItem>getParcelableArrayList(ADAPTER_DATA));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);
        acitivityTitle = getSupportActionBar().getTitle().toString();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        todoListAdapter = new TodoListAdapter();
        recyclerView.setAdapter(todoListAdapter);
        todoListAdapter.setCheckListener(this);

        final EditText editText = (EditText) findViewById(R.id.todo_edit_text);

        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                todoListAdapter.addItem(editText.getText().toString());
                editText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}