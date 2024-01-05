package com.itcraftsolution.romanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.ActivityAddCustomerTransactionBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCustomerTransactionActivity extends AppCompatActivity {

    private ActivityAddCustomerTransactionBinding binding;
    private String custName, moneyStatus;
    private int totalBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCustomerTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();


    }
    private void loadData(){
        totalBalance = getIntent().getIntExtra("totalBalance", 0);
        moneyStatus = getIntent().getStringExtra("moneyStatus");
        custName = getIntent().getStringExtra("custName");

        binding.txCustomerName.setText(custName);
        binding.txAddTransactionMoneyStatus.setText(String.valueOf(moneyStatus + " Balance"));
        binding.txCustomerTotalBalance.setText(String.valueOf("₹ " + totalBalance));

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy-HH:mm a");
        String formattedDateTime = dateFormat.format(currentDate);
        String parts[] = formattedDateTime.split("-");
        String transactionDate = parts[0].trim();
        String transactionTime = parts[1].trim();
        binding.txAddTransactionDate.setText(transactionDate);

    }
}