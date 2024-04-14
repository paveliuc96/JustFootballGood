package com.example.justfootballgood;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonRegister;
    private FirebaseAuth mAuth;
    private CheckBox checkBox;
    private TextView textViewTermsOfUse;


    // function to register using email and password
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(Register.this, Home.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textViewTermsOfUse = findViewById(R.id.txtTermsAndPrivacy);
        checkBox = findViewById(R.id.checkBoxTermsOfUse);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonRegister = findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();

        // function to register using email and password when the button is clicked
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();

                if (TextUtils.isEmpty(email)){
                    editTextEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    editTextPassword.setError("Email is required");
                    return;
                }

                if (!checkBox.isChecked()) {
                    Toast.makeText(Register.this, "You must agree to the Terms of Use.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // If sign is successful, display a message to the user and send him to the login page.
                                    Toast.makeText(Register.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

        // function to display the terms of use when the text is clicked
        textViewTermsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                builder.setTitle("Terms of Use");
                builder.setMessage("This app is for educational purposes only. " +
                        "The app is not responsible for any damage caused by the use of the app. " +
                        "The app is not responsible for any damage caused by the use of the app. " +
                        "The app is not responsible for any damage caused by the use of the app. " +
                        "The app is not responsible for any damage caused by the use of the app. " +
                        "The app is not responsible for any damage caused by the use of the app. " +
                        "The app is not responsible for any damage caused by the use of the app. " +
                        "The app is not responsible for any damage caused by the use of the app. " +
                        "The app is not responsible for any damage caused by the use of the app. " +
                        "The app is not responsible for any damage caused by the use of the app. ");
                builder.setPositiveButton("I agree", null);
                builder.show();
            }
        });
    }
}
