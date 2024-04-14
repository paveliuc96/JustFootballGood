package com.example.justfootballgood;

import static com.example.justfootballgood.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button logout, userSettings;
    TextView userName;
    FirebaseUser user;
    SearchView searchDestination;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.btnLogout);
        userName = findViewById(id.userName);
        user = mAuth.getCurrentUser();
        userSettings = findViewById(R.id.btnSettings);
        searchDestination = findViewById(R.id.searchDestination);

        // Check if user is signed in (non-null) and update UI accordingly.
        if (user == null) {
            Intent intent = new Intent(Home.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            userName.setText(user.getEmail());
        }
        // Configure Google Sign In
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String Name = account.getDisplayName();
            String Email = account.getEmail();

            userName.setText(Name);
        }

        // Logout function
        logout.setOnClickListener(view -> {
            mAuth.signOut();
            gsc.signOut();
            Intent intent = new Intent(Home.this, Login.class);
            startActivity(intent);
            finish();
        });


        userSettings.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, AvatarActivity.class);
            startActivity(intent);
        });

        // Search function sa popti cauta o echipa de fotbal si o sa iti arate statistici despre echipa respectiva , jucatori , meciuri etc pe Londra

        //creeaza o lista cu echipele de fotbal din Londra la care pui
        // creeaza un timer care la fiecare ora iti aopare o fereastra pop up cum ca ai stat prea mult si ca este nevoie sa iesi
        // Butoane Home: meet friends in real life, locations nearby to see the games, tickets to buy, highlights of the games,

    }
}
