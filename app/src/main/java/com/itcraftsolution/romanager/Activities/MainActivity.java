package com.itcraftsolution.romanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayoutMediator;
import com.itcraftsolution.romanager.Adapters.LoginViewPagerAdapter;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.Utils.NetworkChangeListener;
import com.itcraftsolution.romanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LoginViewPagerAdapter adapter;
    private String[] tabTitles = new String[]{"OWNER", "EMPLOYEE"};
    private final NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new LoginViewPagerAdapter(MainActivity.this);
        binding.viewpagerTabs.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLogin, binding.viewpagerTabs, (((tab, position) -> tab.setText(tabTitles[position])))).attach();
    }
    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}