package com.itcraftsolution.romanager.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.itcraftsolution.romanager.Activities.DashboardActivity;
import com.itcraftsolution.romanager.Activities.ProfileActivity;
import com.itcraftsolution.romanager.Models.CustomerModel;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.Utils.FileUtils;
import com.itcraftsolution.romanager.ViewModels.RoManagerViewModel;
import com.itcraftsolution.romanager.databinding.FragmentAddCustomerBinding;

public class AddCustomerFragment extends Fragment {


    private FragmentAddCustomerBinding binding;
    private RoManagerViewModel viewModel;
//    private ActivityResultLauncher<Intent> launcher;
//    private Uri selectedImageUri;
//    private String imgPath = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddCustomerBinding.inflate(getLayoutInflater());

        requireActivity().findViewById(R.id.bottomNavigationView).setVisibility(View.GONE);
        viewModel = ViewModelProviders.of(AddCustomerFragment.this).get(RoManagerViewModel.class);

        binding.edCustomerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    binding.txCustomerNameChar.setText(String.valueOf(Character.toUpperCase(charSequence.charAt(0))));
                } else {
                    binding.txCustomerNameChar.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edCustomerName.getText().toString().isEmpty() || binding.edCustomerName.getText().length() <= 2){
                    binding.edCustomerName.setError("Please enter valid name!!");
                    binding.edCustomerName.requestFocus();
                }else if(!binding.edCustomerPhone.getText().toString().isEmpty() && binding.edCustomerPhone.getText().length() != 10){
                    binding.edCustomerPhone.setError("Please enter valid Phone Number!!");
                    binding.edCustomerPhone.requestFocus();
                }else if(binding.edCustomerAddress.getText().toString().isEmpty() || binding.edCustomerAddress.getText().length() <= 5){
                    binding.edCustomerAddress.setError("Please enter valid Address!!");
                    binding.edCustomerAddress.requestFocus();
                }else{
                    String cust_name = binding.edCustomerName.getText().toString();
                    String cust_phone = binding.edCustomerPhone.getText().toString();
                    String cust_address = binding.edCustomerAddress.getText().toString();
                    CustomerModel model = new CustomerModel(cust_name, cust_phone, cust_address);
                    viewModel.addCustomerDetails(model).observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if(aBoolean){
                                Toast.makeText(requireContext(), "Add Customer Details Successfully...", Toast.LENGTH_SHORT).show();
                                getParentFragmentManager().beginTransaction().replace(R.id.frDashboardContainer, new HomeFragment()).commit();
                            }else{
                                Toast.makeText(requireContext(), "FAC Something went Wrong!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        return binding.getRoot();
    }
}