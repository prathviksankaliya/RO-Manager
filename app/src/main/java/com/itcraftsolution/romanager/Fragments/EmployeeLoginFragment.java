package com.itcraftsolution.romanager.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itcraftsolution.romanager.Activities.MainActivity;
import com.itcraftsolution.romanager.Activities.VerifyOtpActivity;
import com.itcraftsolution.romanager.Preferences.SpfEmployeeDetails;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.FragmentEmployeeLoginBinding;

public class EmployeeLoginFragment extends Fragment {

    private FragmentEmployeeLoginBinding binding;
    private SpfEmployeeDetails spfEmployeeDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEmployeeLoginBinding.inflate(getLayoutInflater());
        binding.btnEmployeeGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edEmployeePhoneNumber.getText().toString().isEmpty() || binding.edEmployeePhoneNumber.getText().length() != 10){
                    binding.edEmployeePhoneNumber.setError("Phone Number Must have 10 Digit!!");
                    binding.edEmployeePhoneNumber.requestFocus();
                }else{
                    spfEmployeeDetails = new SpfEmployeeDetails(requireContext());
                    spfEmployeeDetails.setEmployeePreference(binding.edEmployeePhoneNumber.getText().toString());
                    startActivity(new Intent(requireContext(), VerifyOtpActivity.class));
                }
            }
        });
        return binding.getRoot();
    }
}