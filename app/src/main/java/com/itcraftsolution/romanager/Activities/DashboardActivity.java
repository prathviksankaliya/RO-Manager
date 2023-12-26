package com.itcraftsolution.romanager.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.itcraftsolution.romanager.Fragments.HomeFragment;
import com.itcraftsolution.romanager.Fragments.NotificationFragment;
import com.itcraftsolution.romanager.Fragments.SearchFragment;
import com.itcraftsolution.romanager.Fragments.SettingFragment;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.Utils.NetworkChangeListener;
import com.itcraftsolution.romanager.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private final NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setSelectedItemId(R.id.menuHome);
        getSupportFragmentManager().beginTransaction().replace(R.id.frDashboardContainer, new HomeFragment()).addToBackStack(null).commit();
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment tempFragment = new HomeFragment();
                if(item.getItemId() == R.id.menuHome){
                    tempFragment = new HomeFragment();
                }else if(item.getItemId() == R.id.menuSearch){
                    tempFragment = new SearchFragment();
                }else if(item.getItemId() == R.id.menuNotification){
                    tempFragment = new NotificationFragment();
                }else if(item.getItemId() == R.id.menuSettings){
                    tempFragment = new SettingFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frDashboardContainer, tempFragment).addToBackStack(null).commit();
                return true;
            }
        });

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