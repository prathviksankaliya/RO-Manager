package com.itcraftsolution.romanager.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.itcraftsolution.romanager.databinding.ActivityProfileBinding;

import java.io.File;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private ActivityResultLauncher<Intent> launcher;
    private Uri selectedImageUri;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        loadData();
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
                }else if(selectedImageUri == null){
                    Toast.makeText(ProfileActivity.this, "Please select Plant Image!!", Toast.LENGTH_LONG).show();
                }else{
                    String plantName = binding.edProfilePlantName.getText().toString().trim();
                    String plantCityName = binding.edProfileCityName.getText().toString().trim();
                    String plantAddress = binding.edProfilePlantAddress.getText().toString().trim();
                    Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);


                    intent.putExtra("imageUri", selectedImageUri);
                    intent.putExtra("plantName", plantName);
                    intent.putExtra("plantCity", plantCityName);
                    intent.putExtra("plantAddress", plantAddress);
                    startActivity(intent);
                    finish();
                }
            }
        });

        binding.igEditProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                launcher.launch(intent);
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result-> {
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                if(data != null && data.getData() != null){
                    selectedImageUri = data.getData();
                    final String path = getPathFromUri(selectedImageUri);
                    if(path != null){
                        File f = new File(path);
                        selectedImageUri = Uri.fromFile(f);
                    }
                    Glide.with(ProfileActivity.this).load(selectedImageUri).into(binding.circleImageView);
                }
            }else{
                Toast.makeText(ProfileActivity.this, "Profile Upload Failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void loadData(){
        if(getIntent().getStringExtra("phoneNumber") != null){
            binding.edProfilePhone.setText(getIntent().getStringExtra("phoneNumber"));
            binding.edProfilePhone.setEnabled(false);
        }else if(getIntent().getBooleanExtra("GoogleAuth", false)){
            user = auth.getCurrentUser();
            if(user != null){
                Glide.with(ProfileActivity.this).load(user.getPhotoUrl()).into(binding.circleImageView);
            }
        }
    }
    private String getPathFromUri(Uri contentUri){
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(columnIndex);
        }
        cursor.close();
        return res;
    }
}
