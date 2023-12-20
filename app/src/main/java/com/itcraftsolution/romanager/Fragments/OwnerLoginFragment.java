package com.itcraftsolution.romanager.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.itcraftsolution.romanager.Activities.MainActivity;
import com.itcraftsolution.romanager.Activities.ProfileActivity;
import com.itcraftsolution.romanager.Activities.VerifyOtpActivity;
import com.itcraftsolution.romanager.Preferences.SpfEmployeeDetails;
import com.itcraftsolution.romanager.Preferences.SpfOwnerDetails;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.FragmentEmployeeLoginBinding;
import com.itcraftsolution.romanager.databinding.FragmentOwnerLoginBinding;

public class OwnerLoginFragment extends Fragment {

    private FragmentOwnerLoginBinding binding;
    private GoogleSignInClient googleSignInClient;
    private ActivityResultLauncher<Intent> launcher;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOwnerLoginBinding.inflate(getLayoutInflater());


        auth = FirebaseAuth.getInstance();
        binding.btnGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edOwnerPhoneNumber.getText().toString().isEmpty() || binding.edOwnerPhoneNumber.getText().length() != 10){
                    binding.edOwnerPhoneNumber.setError("Phone Number Must have 10 Digit!!");
                    binding.edOwnerPhoneNumber.requestFocus();
                }else{
                    Intent intent = new Intent(requireContext(), VerifyOtpActivity.class);
                    intent.putExtra("phone", binding.edOwnerPhoneNumber.getText().toString());
                    intent.putExtra("isOwner", true);
                    startActivity(intent);
                }
            }
        });

        binding.btnSignInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions googleSignInOptions =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken("1052096358810-s3hjchfs044ufj7vrfhsibejch54kuj3.apps.googleusercontent.com")
                        .requestEmail()
                        .build();

                googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions);
                Intent intent = googleSignInClient.getSignInIntent();
                launcher.launch(intent);
            }
        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK)
                {
                    Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(result.getData());

                    if(signInAccountTask.isSuccessful())
                    {
                        Toast.makeText(requireContext(), "Google SignIn is Successful !!", Toast.LENGTH_SHORT).show();

                        try {
                            GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                            if(googleSignInAccount != null)
                            {
                                AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                                auth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful())
                                        {
                                            startActivity(new Intent(requireContext(), ProfileActivity.class)
                                                    .putExtra("GoogleAuth", true));
                                            Toast.makeText(requireContext(), "Auth SuccessFul!!", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(requireContext(), "Auth Failed!!", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }
                        } catch (ApiException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(requireContext(), "Failed signIn Account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return binding.getRoot();
    }
}