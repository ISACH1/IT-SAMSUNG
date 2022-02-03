package ru.kirillisachenko.picture;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText et;
    ProgressBar progressBar;
    TextView text;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        et = findViewById(R.id.et);
        progressBar = findViewById(R.id.progress_bar);
        image = findViewById(R.id.image);
        button.setOnClickListener(v -> {
            Thread myThread = new Thread(new MyThread());
            myThread.start();
            Toast.makeText(this, "ALALALAL", Toast.LENGTH_SHORT).show();
        });
    }

    class loadImage{
        public void load() {
            try {
                URL url = new URL(et.getText().toString());
                InputStream inputStream = (InputStream) url.getContent();
                image.setImageBitmap(BitmapFactory.decodeStream(inputStream));
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Повторите попытку!", Toast.LENGTH_SHORT).show();
                et.setText("");
            }
        }
    }

    private class MyThread implements Runnable{
        loadImage loadImage = new loadImage();
        @Override
        public void run() {
            loadImage.load();
        }

    }
}