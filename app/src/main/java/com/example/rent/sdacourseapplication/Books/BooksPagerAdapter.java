package com.example.rent.sdacourseapplication.Books;

import android.content.SharedPreferences;
import android.graphics.pdf.PdfDocument;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;


import com.example.rent.sdacourseapplication.R;

import java.util.List;

/**
 * Created by RENT on 2017-03-02.
 */

public class BooksPagerAdapter extends PagerAdapter {

    private SharedPreferences sharedPreferences;
    public BooksPagerAdapter(List<Book> books) {
        this.books = books;
    }

    public BooksPagerAdapter(List<Book> books, SharedPreferences preferences) {
        this.books = books;
        this.sharedPreferences = preferences;
    }

    private List<Book> books;

    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View pageLayout = inflater.inflate(R.layout.book_page, container, false);
        ImageView image = (ImageView) pageLayout.findViewById(R.id.image);
        image.setImageResource(books.get(position).getImageResourceId());


        CheckBox checkBox = (CheckBox)pageLayout.findViewById(R.id.isRead);
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(books.get(position).isRead());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Book book = books.get(position);
                book.setRead(isChecked);
                sharedPreferences
                        .edit()
                        .putBoolean(String.valueOf(book.getId()), book.isRead())
                        .apply();
            }
        });
        container.addView(pageLayout);
        return pageLayout;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return books.get(position).getTitle();
    }
}
