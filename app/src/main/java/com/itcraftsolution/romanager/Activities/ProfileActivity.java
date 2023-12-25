package com.itcraftsolution.romanager.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.itcraftsolution.romanager.Models.PlantDetailsModel;
import com.itcraftsolution.romanager.Utils.FileUtils;
import com.itcraftsolution.romanager.Utils.NetworkChangeListener;
import com.itcraftsolution.romanager.ViewModels.RoManagerViewModel;
import com.itcraftsolution.romanager.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private ActivityResultLauncher<Intent> launcher;
    private Uri selectedImageUri;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private RoManagerViewModel roManagerViewModel;
    private String authID, imgPath;
    private final NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        roManagerViewModel = ViewModelProviders.of(this).get(RoManagerViewModel.class);

        if(getIntent().getStringExtra("phoneNumber") != null){
            binding.edProfilePhone.setText(getIntent().getStringExtra("phoneNumber"));
            binding.edProfilePhone.setEnabled(false);
        }else if(getIntent().getBooleanExtra("GoogleAuth", false)){
            if(user != null){
                authID = user.getUid();
                selectedImageUri = user.getPhotoUrl();
                imgPath = FileUtils.getPathFromContentUri(ProfileActivity.this, selectedImageUri);
                Glide.with(ProfileActivity.this).load(user.getPhotoUrl()).into(binding.circleImageView);
            }
        }
        binding.btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edProfilePlantName.getText().toString().isEmpty() || binding.edProfilePlantName.getText().length() <= 2){
                    binding.edProfilePlantName.setError("Please Enter Valid Plant Name!!");
                    binding.edProfilePlantName.requestFocus();
                }else if(binding.edProfilePhone.getText().length() != 10){
                    binding.edProfilePhone.setError("Phone Number must have 10 digit!!");
                    binding.edProfilePhone.requestFocus();
                }else if(binding.edProfileCityName.getText().toString().isEmpty() || binding.edProfileCityName.getText().length() <= 2){
                    binding.edProfileCityName.setError("Please Enter Valid City Name!!");
                    binding.edProfileCityName.requestFocus();
                }else if(binding.edProfilePlantAddress.getText().toString().isEmpty() || binding.edProfilePlantAddress.getText().length() <= 7){
                    binding.edProfilePlantAddress.setError("Please Enter Valid Address Name!!");
                    binding.edProfilePlantAddress.requestFocus();
                }else if(selectedImageUri == null && imgPath == null){
                    Toast.makeText(ProfileActivity.this, "Please select Plant Image!!", Toast.LENGTH_LONG).show();
                }else{
                    String plantName = binding.edProfilePlantName.getText().toString().trim();
                    String plantCityName = binding.edProfileCityName.getText().toString().trim();
                    String plantAddress = binding.edProfilePlantAddress.getText().toString().trim();
                    String plantPhone = binding.edProfilePhone.getText().toString().trim();

                    PlantDetailsModel model = new PlantDetailsModel(0, auth.getCurrentUser().getUid(), plantName, plantPhone, "", imgPath, plantCityName, plantAddress, "", 1);
                    roManagerViewModel.insertPlantDetails(model).observe(ProfileActivity.this, new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if(aBoolean){
                                Toast.makeText(ProfileActivity.this, "Plant Details insert Successfully...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                                finish();
                            }else{
                                Toast.makeText(ProfileActivity.this, "PA Something went Wrong!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        binding.igEditProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                launcher.launch(intent);
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result-> {
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                if(data != null && data.getData() != null){
                    selectedImageUri = data.getData();
                    imgPath = FileUtils.getPathFromContentUri(ProfileActivity.this, selectedImageUri);
                    Glide.with(ProfileActivity.this).load(selectedImageUri).into(binding.circleImageView);
                }
            }else{
                Toast.makeText(ProfileActivity.this, "Profile Upload Failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}

