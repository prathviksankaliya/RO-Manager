package com.itcraftsolution.romanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.ActivityVerifyOtpBinding;

public class VerifyOtpActivity extends AppCompatActivity {

    private ActivityVerifyOtpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}