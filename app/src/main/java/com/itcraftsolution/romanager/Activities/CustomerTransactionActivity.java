package com.itcraftsolution.romanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.ActivityCustomerTransactionBinding;

public class CustomerTransactionActivity extends AppCompatActivity {

    private ActivityCustomerTransactionBinding binding;
    private String custName, moneyStatus;
    private int totalBalance, plantId, custId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();

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