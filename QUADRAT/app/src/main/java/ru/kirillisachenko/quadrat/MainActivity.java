package ru.kirillisachenko.quadrat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText aField;
    EditText bField;
    EditText cField;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    public void click(View view){
        double a = Double.parseDouble(aField.getText().toString());
        double b = Double.parseDouble(bField.getText().toString());
        double c = Double.parseDouble(cField.getText().toString());
        double D = b*b - 4*a*c;
        if (D >= 0 ){
            double x1 = (-b + Math.sqrt(D))/(2*a);
            double x2 = (-b - Math.sqrt(D))/(2*a);
            Toast.makeText(this, x2 +"/n" + x1, Toast.LENGTH_SHORT).show();
        }else Toast.makeText(this, "Нет корней", Toast.LENGTH_SHORT).show();

    }


    private void setupUI(){
        aField = findViewById(R.id.a_field);
        bField = findViewById(R.id.b_field);
        cField = findViewById(R.id.c_field);
        button = findViewById(R.id.button);
    }


}
