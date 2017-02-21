package com.example.rent.sdacourseapplication.gallery;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.rent.sdacourseapplication.R;
import com.example.rent.sdacourseapplication.drawing.DrawingActivity;

import java.io.File;

public class GalleryActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        File dir = getExternalFilesDir(DrawingActivity.MY_DRAWING_DIR);
        File[] files = dir.listFiles();
        viewPager.setAdapter(new DrawingPagerAdapter(files));


    }
}
