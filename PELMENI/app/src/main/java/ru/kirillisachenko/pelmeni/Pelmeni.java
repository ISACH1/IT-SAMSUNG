package ru.kirillisachenko.pelmeni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pelmeni extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelmeni);
        setupUI();
    }
    private void setupUI(){
        Button main = findViewById(R.id.main_menu);
        main.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_menu){
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
        }
    }
}