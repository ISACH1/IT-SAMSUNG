package ru.kirillisachenko.virusgame.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ru.kirillisachenko.virusgame.Game;
import ru.kirillisachenko.virusgame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setButtons();

    }

    public void setButtons(){
        binding.startGame.setOnClickListener(v -> startActivity(new Intent(this, ChooseHeroActivity.class)));
        binding.exit.setOnClickListener(v -> System.out.println(1/0));
        binding.settings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }
}