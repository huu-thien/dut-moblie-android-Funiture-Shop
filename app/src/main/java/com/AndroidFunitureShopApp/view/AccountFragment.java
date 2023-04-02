package com.AndroidFunitureShopApp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.AndroidFunitureShopApp.MainActivity;
import com.AndroidFunitureShopApp.R;
import com.AndroidFunitureShopApp.RegisterActivity;
import com.AndroidFunitureShopApp.databinding.FragmentAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    String Uid = mAuth.getUid();
    DocumentReference df = fStore.collection("User").document(Uid);

    public AccountFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShowInfo(Uid);
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = binding.etPassword.getText().toString();
                String Email = mAuth.getCurrentUser().getEmail();
                if (!pass.equals("")) {
                    AuthCredential credential = EmailAuthProvider
                            .getCredential(Email, pass);
                    // Prompt the user to re-provide their sign-in credentials
                    mAuth.getCurrentUser().reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        UpdateInfo(Uid);
                                        ShowInfo(Uid);
                                        Toast.makeText(getActivity(), "Update Success!", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(), "Wrong or Missing information!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(getActivity(), "Wrong or Missing information!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void ShowInfo(String Uid){
        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                binding.etEmail.setText(document.getString("Email"));
                binding.etUsername.setText(document.getString("Name"));
                binding.etFullName.setText(document.getString("Fullname"));
                binding.etPhone.setText(document.getString("Phonenumber"));
            }
        });
    }

    public void UpdateInfo(String Uid){
        df.update("Name", binding.etUsername.getText().toString());
        df.update("Fullname", binding.etFullName.getText().toString());
        df.update("Phonenumber", binding.etPhone.getText().toString());
        df.update("Email", binding.etEmail.getText().toString());
        if(!binding.etNewPassword.getText().toString().isEmpty()){
            mAuth.getCurrentUser().updatePassword(binding.etNewPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Update Success!", Toast.LENGTH_SHORT).show();
                                ShowInfo(Uid);
                            }
                            else {
                                Toast.makeText(getActivity(), "Wrong or Missing information!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}