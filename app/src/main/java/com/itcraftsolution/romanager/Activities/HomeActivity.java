package com.itcraftsolution.romanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(HomeActivity.this).load(getIntent().getStringExtra("imageUri")).into(binding.igDemo);
        String demoTxt = getIntent().getStringExtra("plantName") +
                "\n" + getIntent().getStringExtra("plantCity") + "\n" +
                getIntent().getStringExtra("plantAddress");
        binding.txDemo.setText(demoTxt);
    }
}