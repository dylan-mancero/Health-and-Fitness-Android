package com.hfs.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hfs.lib.Fitness;
import com.hfs.lib.Goal;
import com.hfs.lib.Schedule;
import com.hfs.lib.StandardProfile;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.UnfinishedActivity;
import com.hfs.lib.nutrition.Nutrition;
import com.hfs.lib.repo.Consumables;
import com.hfs.lib.repo.Exercises;
import com.hfs.lib.repo.Sports;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class LoginActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText2);
        password = findViewById(R.id.editText3);
        btnSignIn = findViewById(R.id.button);
        tvSignUp = findViewById(R.id.textView);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    // TODO: Remove dummy values;
                    final Nutrition nutrition = new Nutrition(100,101,102, 103, null);
                    final Schedule schedule = new Schedule();
                    final Fitness fitness = new Fitness();

                    final StandardProfile profile = new StandardProfile(
                            nutrition,
                            schedule,
                            fitness,
                            Sports.getInstance(),
                            Exercises.getInstance(),
                            Consumables.getInstance());

                    profile.setGoal(Goal.GAIN_MUSCLE_MASS);

                    final UnfinishedActivity dummyUnfinishedActivity = new UnfinishedActivity(
                            Sports.getInstance().getSport("Running"),
                            OffsetDateTime.of(2020, 1, 1, 10, 10, 10, 0, ZoneOffset.UTC));
                    final FinishedActivity dummyFinishedActivity = dummyUnfinishedActivity.end();

                    profile.addPastActivitySession(dummyFinishedActivity);
                    profile.consume(Consumables.getInstance().getConsumable("Pasta"), 100);

                    ((HFSApplication) getApplication()).initStandardProfile(profile);
                    Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                final StandardProfile profile = new StandardProfile(new Nutrition(0,0,0,0,null),new Schedule(), new Fitness(), Sports.getInstance(), Exercises.getInstance(), Consumables.getInstance());
                                ((HFSApplication) getApplication()).initStandardProfile(profile);
                                Intent intToHome = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}

