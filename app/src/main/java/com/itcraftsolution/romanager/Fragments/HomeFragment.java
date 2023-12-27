package com.itcraftsolution.romanager.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        requireActivity().findViewById(R.id.bottomNavigationView).setVisibility(View.VISIBLE);
        binding.fabAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.frDashboardContainer, new AddCustomerFragment()).addToBackStack(null).commit();
            }
        });
        return binding.getRoot();
    }
}