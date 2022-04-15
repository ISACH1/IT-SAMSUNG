package ru.kirillisachenko.virusgame.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.gtomato.android.ui.transformer.FlatMerryGoRoundTransformer;
import com.gtomato.android.ui.widget.CarouselView;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.Activities.Adapters.CarouselRecyclerAdapter;
import ru.kirillisachenko.virusgame.Game;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.databinding.ActivityChooseHeroBinding;

public class ChooseHeroActivity extends AppCompatActivity {
    ActivityChooseHeroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseHeroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.carousel.setTransformer(new FlatMerryGoRoundTransformer());
        ArrayList<Integer> p = new ArrayList<>();
        p.add(R.drawable.virus_state);
        p.add(R.drawable.ninja_state);
        binding.carousel.setAdapter(new CarouselRecyclerAdapter());
        binding.carousel.setOnItemClickListener((adapter, view, i, i1) -> setContentView(new Game(ChooseHeroActivity.this, i)));
    }
}