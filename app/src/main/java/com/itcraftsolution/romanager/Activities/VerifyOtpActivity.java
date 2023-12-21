package com.itcraftsolution.romanager.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.itcraftsolution.romanager.Preferences.SpfEmployeeDetails;
import com.itcraftsolution.romanager.Preferences.SpfOwnerDetails;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.ActivityVerifyOtpBinding;

import java.util.concurrent.TimeUnit;

public class VerifyOtpActivity extends AppCompatActivity {

    private ActivityVerifyOtpBinding binding;
    private String phoneNumber, verifyId;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        phoneNumber = "+91" + getIntent().getStringExtra("phone");
        binding.txOtpMsg.setText(String.valueOf("Please enter the verification code that we just sent you at (" + phoneNumber + ")"));

        auth = FirebaseAuth.getInstance();
        sendVerificationCode(phoneNumber);

        binding.btnVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkOtp())
                {
                    verifyCode(binding.otpView.getOTP());
                }
            }
        });

    }

    private void sendVerificationCode(String phone){
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setActivity(VerifyOtpActivity.this)
                .setPhoneNumber(phone)
                .setCallbacks(callbacks)
                .setTimeout(30L, TimeUnit.SECONDS)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                binding.otpView.setOTP(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyOtpActivity.this, "Something went wrong!! " + e, Toast.LENGTH_SHORT).show();
            Log.d("FirebaseEx", e.toString());
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verifyId = s;
        }
    };

    private void verifyCode(String code){
//        PhoneAuthCredential credential =PhoneAuthProvider.getCredential(verifyId, String.valueOf("+91" + code));
        PhoneAuthCredential credential =PhoneAuthProvider.getCredential(verifyId,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(VerifyOtpActivity.this, ProfileActivity.class).putExtra("phoneNumber", getIntent().getStringExtra("phone")));
                }else {
                    Toast.makeText(VerifyOtpActivity.this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkOtp() {
        if (binding.otpView.getOTP().length() != 6) {
            Toast.makeText(VerifyOtpActivity.this, "Fill The OTP", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}