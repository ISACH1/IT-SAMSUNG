package ru.kirillisachenko.pelmeni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registr extends AppCompatActivity implements View.OnClickListener {

    EditText logintext;
    EditText passwordtext;
    EditText passwordcontroltext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);
        setupUI();
    }
    private void setupUI(){
         logintext = findViewById(R.id.login_text);
         passwordtext = findViewById(R.id.password_text);
         passwordcontroltext = findViewById(R.id.password_control_text);
         Button reg = findViewById(R.id.button_reg);
         reg.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_reg:
                String login = logintext.getText().toString();
                String password = passwordtext.getText().toString();
                String password1 = passwordcontroltext.getText().toString();
                if (login != null && password != null){
                  if (password.equals(password1)) {
                    Intent i = new Intent(this, Login.class);
                    i.putExtra("log", login);
                    i.putExtra("pass", password);
                    startActivity(i);
                  } else {
                    Toast.makeText(this, "Проверьте правильность заполения полей", Toast.LENGTH_SHORT).show();
                  }
            }else {
                    Toast.makeText(this, "Проверьте правильность заполения полей", Toast.LENGTH_SHORT).show();
                }
        }
    }
}