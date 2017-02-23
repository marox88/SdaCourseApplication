package com.example.rent.sdacourseapplication.todo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.rent.sdacourseapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-02-22.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.MyViewHolder> {


    private List<TodoListItem> items = new ArrayList<>();



    private OnItemCheckStateChanged checkListener;

    public void setCheckListener(OnItemCheckStateChanged checkListener) {
        this.checkListener = checkListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final TodoListItem listItem = items.get(position);
        holder.textView.setText(items.get(position).getText());
        holder.checkbox.setChecked(listItem.isChecked());
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listItem.setChecked(isChecked);
                if(checkListener != null) {
                    checkListener.onItemCheckStateChanged(getCheckedItemsCount());
                }
            }
        });
        ;
    }

    private int getCheckedItemsCount() {
        int count = 0;

        for(TodoListItem item : items) {
            if(item.isChecked()) {
                count++;
            }

        }
        return count;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(String item) {
        items.add(new TodoListItem(item));
        notifyDataSetChanged();

    }

    public void deleteAllCheckedItems() {

        List<TodoListItem> newListItems = new ArrayList<>();
        for(int i = 0; i < items.size(); i++) {
            if (!items.get(i).isChecked()) {
                newListItems.add(items.get(i));
            }

        }
        items = newListItems;
        notifyDataSetChanged();
        if(checkListener != null) {
            checkListener.onItemCheckStateChanged(getCheckedItemsCount());
        }
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        CheckBox checkbox;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }




}
