package com.itcraftsolution.romanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayoutMediator;
import com.itcraftsolution.romanager.Adapters.LoginViewPagerAdapter;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LoginViewPagerAdapter adapter;
    private String[] tabTitles = new String[]{"OWNER", "EMPLOYEE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new LoginViewPagerAdapter(MainActivity.this);
        binding.viewpagerTabs.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLogin, binding.viewpagerTabs, (((tab, position) -> tab.setText(tabTitles[position])))).attach();
    }
}