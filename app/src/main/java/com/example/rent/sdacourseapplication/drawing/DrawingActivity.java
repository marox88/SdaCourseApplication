package com.example.rent.sdacourseapplication.drawing;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rent.sdacourseapplication.R;
import com.example.rent.sdacourseapplication.gallery.GalleryActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DrawingActivity extends AppCompatActivity {

    private SimpleDrawingView simpleDrawingView;
    public static String MY_DRAWING_DIR = "my_drawing_dir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_activity_main);
        getSupportActionBar().setTitle("RYSOWANIE");
        simpleDrawingView = (SimpleDrawingView) findViewById(R.id.drawing_view);
        Button clearButton = (Button) findViewById(R.id.clear_button);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.clear();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.clear_menu) {
            simpleDrawingView.clear();
        }else if (item.getItemId() == R.id.save) {
            saveDrawingtoFile();
        } else if (item.getItemId() == R.id.drawing_gallery) {
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveDrawingtoFile() {
        File drawingFile = new File(getDrawingGalleryDirectory(), createFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(drawingFile);
            Bitmap bitmap = convertViewToBitmap(simpleDrawingView);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Toast.makeText(DrawingActivity.this, "zapisano", Toast.LENGTH_SHORT).show();

    }

    private String createFileName(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "my_drawing" + timeStamp + ".png";
    }


    private File getDrawingGalleryDirectory() {
        return getExternalFilesDir(MY_DRAWING_DIR);
    }

    private Bitmap convertViewToBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;

    }



}
