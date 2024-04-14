package com.example.justfootballgood;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AvatarActivity extends AppCompatActivity {
    TextView username, changePassword, deleteAccount, myDetails, helpAndSupport, termsAndConditions, privacyPolicy, appInformation;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        username = findViewById(R.id.txtUserName);
        changePassword = findViewById(R.id.txtChangePassword);
        deleteAccount = findViewById(R.id.txtDeleteAccount);
        myDetails = findViewById(R.id.txtMyDetails);
        helpAndSupport = findViewById(R.id.txtHelp);
        termsAndConditions = findViewById(R.id.txtTermsAndConditions);
        privacyPolicy = findViewById(R.id.txtPrivacyPolicy);
        appInformation = findViewById(R.id.txtAppInformation);
        home = findViewById(R.id.btnHome);


        // Get the current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username.setText(user.getEmail());


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AvatarActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        });


        // function to change the password from the account activity page
        // this action is done using internal Dialog
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText resetPassword = new EditText(view.getContext());

                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Change Password");
                passwordResetDialog.setMessage("Enter new password");
                passwordResetDialog.setView(resetPassword);

                passwordResetDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newPassword = resetPassword.getText().toString();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AvatarActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Close the dialog
                    }
                });

                passwordResetDialog.create().show();
            }
        });
    }
}
