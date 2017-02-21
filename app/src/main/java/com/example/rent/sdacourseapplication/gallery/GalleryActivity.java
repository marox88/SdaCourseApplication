package com.example.rent.sdacourseapplication.gallery;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rent.sdacourseapplication.R;
import com.example.rent.sdacourseapplication.drawing.DrawingActivity;

import java.io.File;

public class GalleryActivity extends AppCompatActivity{

    private DrawingPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        File dir = getExternalFilesDir(DrawingActivity.MY_DRAWING_DIR);
        File[] files = dir.listFiles();
        pagerAdapter = new DrawingPagerAdapter(files);
        viewPager.setAdapter(pagerAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gallery_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            pagerAdapter.deleteItem(viewPager.getCurrentItem());
        }
        return super.onOptionsItemSelected(item);
    }
}
