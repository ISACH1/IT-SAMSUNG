package ru.kirillisachenko.pelmeni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText login;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUI();
    }
    private void setupUI(){
        login = findViewById(R.id.login_login);
        password = findViewById(R.id.password_login);
        Button button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                String pass = getIntent().getStringExtra("pass");
                String log = getIntent().getStringExtra("log");
                String l = login.getText().toString();
                String p = password.getText().toString();
                if (l.equals(log) && p.equals(pass)){
                    Intent prlmeni = new Intent(this, Pelmeni.class);
                    prlmeni.putExtra("name" , log);
                    startActivity(prlmeni);
                } else{
                    Toast.makeText(this, "Повторите попытку(пока вы недостойны пельменей)", Toast.LENGTH_SHORT).show();
                }

        }
    }
}