package com.AndroidFunitureShopApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.AndroidFunitureShopApp.databinding.ActivityRegister2Binding;
import com.AndroidFunitureShopApp.databinding.LoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private ActivityRegister2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister2Binding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        mAuth = FirebaseAuth.getInstance();

        binding.etreenter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String oldPass = binding.etPassword.getText().toString();
                String confirmPass  = binding.etreenter.getText().toString();
                if (!oldPass.equals("") && !confirmPass.equals("") && !oldPass.equals(confirmPass)) {
                    Toast.makeText(RegisterActivity.this, "RePassword and Password not equal!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.cbisAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    binding.cbisUser.setChecked(false);
                }
            }
        });

        binding.cbisUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    binding.cbisAdmin.setChecked(false);
                }
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString().trim();
                String pass = binding.etPassword.getText().toString().trim();
                String confirmPass  = binding.etreenter.getText().toString();
                if (!email.equals("") && !pass.equals("") && !confirmPass.equals("") && pass.equals(confirmPass)) {
                    createNewUser(email, pass);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Wrong information!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void createNewUser (String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_LONG).show();
                            DocumentReference df = fStore.collection("User").document(user.getUid());
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("Name", binding.etName.getText().toString());
                            userInfo.put("Email", binding.etEmail.getText().toString());
                            if(binding.cbisAdmin.isChecked()){
                                userInfo.put("isAdmin", "1");
                            }
                            if(binding.cbisUser.isChecked()){
                                userInfo.put("isUser", "1");
                            }
                            df.set(userInfo);
                        }else {
                            Toast.makeText(RegisterActivity.this, "Register Fail", Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(RegisterActivity.this, "User with this email already exist.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    public void UpdateUser( ){
        FirebaseUser user = mAuth.getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Update Success", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Update Fail", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}