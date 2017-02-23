package com.example.rent.sdacourseapplication.todo;

/**
 * Created by RENT on 2017-02-23.
 */

public class TodoListItem {


    private String text;
    private boolean isChecked;


    public void setText(String text) {
        this.text = text;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getText() {
        return text;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
