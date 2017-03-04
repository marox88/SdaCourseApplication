package com.example.rent.sdacourseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.rent.sdacourseapplication.Books.BooksActivity;
import com.example.rent.sdacourseapplication.drawing.DrawingActivity;
import com.example.rent.sdacourseapplication.flaga.FlagaActivity;
import com.example.rent.sdacourseapplication.fortune.FortuneActivity;
import com.example.rent.sdacourseapplication.game.GameActivity;
import com.example.rent.sdacourseapplication.milionerzy.QuizActivity;
import com.example.rent.sdacourseapplication.todo.ToDoListActivity;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);

        TextView textView = (TextView) findViewById(R.id.rysowanie);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DrawingActivity.class);
                startActivity(intent);
            }
        });

        TextView textView2 = (TextView) findViewById(R.id.granie);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        TextView textView3 = (TextView) findViewById(R.id.flaga);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FlagaActivity.class);
                startActivity(intent);
            }
        });

        TextView textView4 = (TextView) findViewById(R.id.todo_list_application);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ToDoListActivity.class);
                startActivity(intent);
            }
        });

        TextView textView5 = (TextView) findViewById(R.id.milionerzy_button);
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        TextView textView6 = (TextView) findViewById(R.id.books);
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BooksActivity.class);
                startActivity(intent);
            }
        });
        TextView textView7 = (TextView) findViewById(R.id.fortune);
        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FortuneActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
