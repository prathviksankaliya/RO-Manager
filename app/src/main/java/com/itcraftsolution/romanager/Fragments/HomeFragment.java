package com.itcraftsolution.romanager.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.itcraftsolution.romanager.Adapters.HomeCustomerRecyclerAdapter;
import com.itcraftsolution.romanager.Models.CustomerResponse;
import com.itcraftsolution.romanager.Models.PlantDetailsModel;
import com.itcraftsolution.romanager.Models.PlantResponse;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.Retrofit.RetrofitInstance;
import com.itcraftsolution.romanager.ViewModels.RoManagerViewModel;
import com.itcraftsolution.romanager.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RoManagerViewModel viewModel;
    private HomeCustomerRecyclerAdapter adapter;
    private FirebaseAuth auth;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        auth = FirebaseAuth.getInstance();
        requireActivity().findViewById(R.id.bottomNavigationView).setVisibility(View.VISIBLE);
        viewModel = ViewModelProviders.of(HomeFragment.this).get(RoManagerViewModel.class);

        viewModel.getAllPlantDetails(auth.getCurrentUser().getUid()).observe(getViewLifecycleOwner(), new Observer<PlantResponse>() {
            @Override
            public void onChanged(PlantResponse plantResponse) {
                if(plantResponse != null){
                    if(plantResponse.getPlantDetails() != null){
                        PlantDetailsModel model = plantResponse.getPlantDetails();
                        binding.txPlantName.setText(model.getPlant_name());
                        bundle = new Bundle();
                        bundle.putInt("plant_id", model.getPlant_id());
                        Glide.with(requireContext()).load(RetrofitInstance.BASE_URL + model.getPlant_image()).into(binding.igPlantImage);
                    }
                }
            }
        });

        viewModel.getAllCustomers().observe(getViewLifecycleOwner(), new Observer<CustomerResponse>() {
            @Override
            public void onChanged(CustomerResponse customerResponse) {
                if (customerResponse != null) {
                    if (customerResponse.getStatus().equals("success")) {
                        if (customerResponse.getCustomerDetails().isEmpty()) {
                            binding.rvCustomerList.setVisibility(View.GONE);
                            binding.llNotFound.setVisibility(View.VISIBLE);
                        } else {
                            binding.llNotFound.setVisibility(View.GONE);
                            binding.rvCustomerList.setVisibility(View.VISIBLE);
                            adapter = new HomeCustomerRecyclerAdapter(requireContext(), customerResponse.getCustomerDetails());
                            binding.rvCustomerList.setLayoutManager(new LinearLayoutManager(requireContext()));
                            binding.rvCustomerList.setHasFixedSize(false);
                            binding.rvCustomerList.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(requireContext(), customerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Rv_Customer", "Home Rv : " + customerResponse.getMessage());
                    }
                }
            }
        });

        binding.fabAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addCustomerFragment = new AddCustomerFragment();
                addCustomerFragment.setArguments(bundle);
                getParentFragmentManager().beginTransaction().replace(R.id.frDashboardContainer, addCustomerFragment).addToBackStack(null).commit();

            }
        });
        return binding.getRoot();
    }
}