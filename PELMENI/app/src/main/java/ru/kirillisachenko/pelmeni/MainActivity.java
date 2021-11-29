package ru.kirillisachenko.pelmeni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private  void setupUi(){
        Button registration = findViewById(R.id.registration);
        registration.setOnClickListener(this);
        Button login = findViewById(R.id.login);
        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registration:
                Intent registration = new Intent(this, Registr.class);
                startActivity(registration);
                break;
            case R.id.login:
                Intent login = new Intent(this, Login.class);
                startActivity(login);
                break;
        }

    }
}