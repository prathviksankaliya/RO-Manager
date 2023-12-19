package com.itcraftsolution.romanager.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itcraftsolution.romanager.Activities.VerifyOtpActivity;
import com.itcraftsolution.romanager.Preferences.SpfEmployeeDetails;
import com.itcraftsolution.romanager.Preferences.SpfOwnerDetails;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.FragmentEmployeeLoginBinding;
import com.itcraftsolution.romanager.databinding.FragmentOwnerLoginBinding;

public class OwnerLoginFragment extends Fragment {

    private FragmentOwnerLoginBinding binding;
    private SpfOwnerDetails spfOwnerDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOwnerLoginBinding.inflate(getLayoutInflater());

        binding.btnGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edOwnerPhoneNumber.getText().toString().isEmpty() || binding.edOwnerPhoneNumber.getText().length() != 10){
                    binding.edOwnerPhoneNumber.setError("Phone Number Must have 10 Digit!!");
                    binding.edOwnerPhoneNumber.requestFocus();
                }else{
                    spfOwnerDetails = new SpfOwnerDetails(requireContext());
                    spfOwnerDetails.setOwnerPreference(binding.edOwnerPhoneNumber.getText().toString());
                    startActivity(new Intent(requireContext(), VerifyOtpActivity.class));
                }
            }
        });
        return binding.getRoot();
    }
}