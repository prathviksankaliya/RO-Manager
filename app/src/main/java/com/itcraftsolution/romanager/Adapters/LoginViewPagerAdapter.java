package com.itcraftsolution.romanager.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.itcraftsolution.romanager.Fragments.EmployeeLoginFragment;
import com.itcraftsolution.romanager.Fragments.OwnerLoginFragment;

public class LoginViewPagerAdapter extends FragmentStateAdapter {
    private String tabs[] = new String[]{"OWNER", "EMPLOYEE"};

    public LoginViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new OwnerLoginFragment();
        }else{
            return new EmployeeLoginFragment();
        }
    }

    @Override
    public int getItemCount() {
        return tabs.length;
    }
}
