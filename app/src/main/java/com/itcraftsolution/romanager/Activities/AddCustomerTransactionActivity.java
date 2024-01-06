package com.itcraftsolution.romanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.ActivityAddCustomerTransactionBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCustomerTransactionActivity extends AppCompatActivity {

    private ActivityAddCustomerTransactionBinding binding;
    private String custName, moneyStatus;
    private int totalBalance;
    private int bottlePrice = 20, jagPrice = 25;
    private int totalPriceOfJag = 0, totalPriceOfBottle = 0, temp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCustomerTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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