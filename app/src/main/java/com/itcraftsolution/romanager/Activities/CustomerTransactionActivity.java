package com.itcraftsolution.romanager.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.itcraftsolution.romanager.Adapters.CustomerTransactionRecyclerAdapter;
import com.itcraftsolution.romanager.Adapters.HomeCustomerRecyclerAdapter;
import com.itcraftsolution.romanager.Models.CustomerTransactionResponse;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.ViewModels.RoManagerViewModel;
import com.itcraftsolution.romanager.databinding.ActivityCustomerTransactionBinding;

public class CustomerTransactionActivity extends AppCompatActivity {

    private ActivityCustomerTransactionBinding binding;
    private String custName, moneyStatus;
    private int totalBalance, plantId, custId;
    private RoManagerViewModel viewModel;
    private CustomerTransactionRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();

        viewModel = ViewModelProviders.of(CustomerTransactionActivity.this).get(RoManagerViewModel.class);

        viewModel.getCustomerTransactionDetails(custId, plantId).observe(this, new Observer<CustomerTransactionResponse>() {
            @Override
            public void onChanged(CustomerTransactionResponse customerTransactionResponse) {
                if (customerTransactionResponse != null) {
                    if (customerTransactionResponse.getStatus().equals("success")) {
                        if (customerTransactionResponse.getCustomerTransactionModels().isEmpty()) {
                            binding.rvCustomerTransactions.setVisibility(View.GONE);
                            binding.llNotFoundTransaction.setVisibility(View.VISIBLE);
                        } else {
                            binding.llNotFoundTransaction.setVisibility(View.GONE);
                            binding.rvCustomerTransactions.setVisibility(View.VISIBLE);
                            adapter = new CustomerTransactionRecyclerAdapter(CustomerTransactionActivity.this, customerTransactionResponse.getCustomerTransactionModels());
                            binding.rvCustomerTransactions.setLayoutManager(new LinearLayoutManager(CustomerTransactionActivity.this));
                            binding.rvCustomerTransactions.setHasFixedSize(false);
                            binding.rvCustomerTransactions.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(CustomerTransactionActivity.this, customerTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        binding.btnTransactionGiven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerTransactionActivity.this, AddCustomerTransactionActivity.class)
                        .putExtra("custName", custName)
                        .putExtra("moneyStatus", moneyStatus)
                        .putExtra("totalBalance", totalBalance)
                        .putExtra("plantId", plantId)
                        .putExtra("custId", custId));
                finish();
            }
        });
    }

    private void loadData(){
        totalBalance = getIntent().getIntExtra("totalBalance", 0);
        moneyStatus = getIntent().getStringExtra("moneyStatus");
        custName = getIntent().getStringExtra("custName");
        plantId = getIntent().getIntExtra("plantId", 0);
        custId = getIntent().getIntExtra("custId", 0);

        binding.txCustomerName.setText(custName);
        binding.txCustTransactionMoneyStatus.setText(String.valueOf(moneyStatus + " Balance"));
        binding.txCustTransactionBalance.setText(String.valueOf("₹ " + totalBalance));
    }
}