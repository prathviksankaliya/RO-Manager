package com.itcraftsolution.romanager.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.itcraftsolution.romanager.Models.CustomerTransactionModel;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.ViewModels.RoManagerViewModel;
import com.itcraftsolution.romanager.databinding.ActivityAddCustomerTransactionBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCustomerTransactionActivity extends AppCompatActivity {

    private ActivityAddCustomerTransactionBinding binding;
    private String custName, moneyStatus, formattedDateTime;
    private int totalBalance, plantId, custId;
    private int bottlePrice = 20, jagPrice = 25;
    private int totalPriceOfJag = 0, totalPriceOfBottle = 0, temp = 0;
    private RoManagerViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCustomerTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(RoManagerViewModel.class);
        loadData();

        binding.btnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.edJag.getText().toString().isEmpty() && Integer.parseInt(binding.edJag.getText().toString()) <= 0){
                    binding.edJag.setError("Plz Enter Valid Number of Jag!!");
                    binding.edJag.requestFocus();
                }else if(!binding.edBottle.getText().toString().isEmpty() && Integer.parseInt(binding.edBottle.getText().toString()) <= 0){
                    binding.edBottle.setError("Plz Enter Valid Number of Bottle!!");
                    binding.edBottle.requestFocus();
                }else{
                    int jag = 0, bottle = 0;
                    String note = "";
                    if(binding.edJag.getText().toString().isEmpty() && !binding.edBottle.getText().toString().isEmpty()){
                        bottle = Integer.parseInt(binding.edBottle.getText().toString());
                    }else if(binding.edBottle.getText().toString().isEmpty() && !binding.edJag.getText().toString().isEmpty()){
                        jag = Integer.parseInt(binding.edJag.getText().toString());
                    }else {
                        jag = Integer.parseInt(binding.edJag.getText().toString());
                        bottle = Integer.parseInt(binding.edBottle.getText().toString());
                    }
                    if(binding.edAddTransactionNotes.getText().toString().isEmpty()){
                        note = "";
                    }else{
                        note = binding.edAddTransactionNotes.getText().toString();
                    }
                    int debit = (jag * jagPrice) + (bottlePrice * bottle);
                    Toast.makeText(AddCustomerTransactionActivity.this, ""+debit, Toast.LENGTH_SHORT).show();
                    CustomerTransactionModel model = new CustomerTransactionModel(custId, plantId, debit, totalBalance, jag, bottle, note, formattedDateTime);
                    totalBalance = totalBalance - debit;
                    viewModel.addCustomerGivenTransaction(model).observe(AddCustomerTransactionActivity.this, new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if(aBoolean){
                                startActivity(new Intent(AddCustomerTransactionActivity.this, CustomerTransactionActivity.class)
                                        .putExtra("custName", custName)
                                        .putExtra("moneyStatus", moneyStatus)
                                        .putExtra("totalBalance", totalBalance)
                                        .putExtra("plantId", plantId)
                                        .putExtra("custId", custId));
                                finish();
                            }else{
                                Toast.makeText(AddCustomerTransactionActivity.this, "ACT Something went Wrong!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        binding.edJag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = totalPriceOfBottle;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 0){
                    int noOfJag = Integer.parseInt(editable.toString());
                    totalPriceOfJag = noOfJag * jagPrice;
                    if(temp != 0){
                        binding.edAddTransaction.setText(String.valueOf("₹ "+ (temp + totalPriceOfJag)));
                    }else{
                        binding.edAddTransaction.setText(String.valueOf("₹ "+totalPriceOfJag));
                    }
                }else {
                    binding.edAddTransaction.setText(String.valueOf("₹ "+temp));
                }
            }
        });

        binding.edBottle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = totalPriceOfJag;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 0){
                    int noOfBottle = Integer.parseInt(editable.toString());
                    totalPriceOfBottle = noOfBottle * bottlePrice;
                    if(temp != 0){
                        binding.edAddTransaction.setText(String.valueOf("₹ "+ (temp + totalPriceOfBottle)));
                    }else{
                        binding.edAddTransaction.setText(String.valueOf("₹ "+totalPriceOfBottle));
                    }
                }else{
                    binding.edAddTransaction.setText(String.valueOf("₹ "+temp));
                }
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
        binding.txAddTransactionMoneyStatus.setText(String.valueOf(moneyStatus + " Balance"));
        binding.txCustomerTotalBalance.setText(String.valueOf("₹ " + totalBalance));

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy-HH:mm a");
        formattedDateTime = dateFormat.format(currentDate);
        String parts[] = formattedDateTime.split("-");
        String transactionDate = parts[0].trim();
        String transactionTime = parts[1].trim();
        binding.txAddTransactionDate.setText(transactionDate);

    }
}