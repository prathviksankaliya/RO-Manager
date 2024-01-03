package com.itcraftsolution.romanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.ActivityAddCustomerTransactionBinding;

public class AddCustomerTransactionActivity extends AppCompatActivity {

    private ActivityAddCustomerTransactionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCustomerTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}