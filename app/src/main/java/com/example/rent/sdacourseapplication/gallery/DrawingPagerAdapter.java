package com.example.rent.sdacourseapplication.gallery;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rent.sdacourseapplication.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DrawingPagerAdapter extends PagerAdapter{

    private final File[] files;

    public DrawingPagerAdapter (File[] files) {
        this.files = files;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.single_page_item, container, false);
        ImageView image = (ImageView) view.findViewById(R.id.drawing_image);

        try {
            FileInputStream fileInputStream = new FileInputStream(files[position]);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            image.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
