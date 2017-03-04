package com.example.rent.sdacourseapplication.Books;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rent.sdacourseapplication.R;

import java.util.Arrays;
import java.util.List;

public class BooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        ViewPager viewPager = (ViewPager) findViewById(R.id.books_view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Book effective = new Book(1, R.drawable.effective, "Effective Java");
        effective.setRead(sharedPreferences.getBoolean(String.valueOf(effective.getId()), false));
        Book cleanCode = new Book (2, R.drawable.clean, "Clean Code");
        cleanCode.setRead(sharedPreferences.getBoolean(String.valueOf(effective.getId()), false));
        Book head = new Book (3, R.drawable.head, "Laska wpierw");
        head.setRead(sharedPreferences.getBoolean(String.valueOf(effective.getId()), false));

        List<Book> list = Arrays.asList(effective, cleanCode, head);

        BooksPagerAdapter adapter = new BooksPagerAdapter(list, sharedPreferences);

        viewPager.setAdapter(adapter);
    }
}
