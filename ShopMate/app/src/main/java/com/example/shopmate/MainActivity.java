package com.example.shopmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView register,forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();


        register=(TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn= (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmail= (EditText) findViewById(R.id.email);
        editTextPassword=(EditText) findViewById(R.id.password);

        forgotPassword=(TextView) findViewById(R.id.forgotpassword);
        forgotPassword.setOnClickListener(this);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.register:
                startActivity(new Intent(this,registerUser.class));
                break;

            case R.id.signIn:
                userLogin();
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this,ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            editTextPassword.setError("Minimum Password Length Should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified())
                    {

                        startActivity(new Intent(MainActivity.this,Menu.class));
                    }
                    else
                    {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this,"Check your email to verify your Account!",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);

                    }
                }else
                {
                    Toast.makeText(MainActivity.this,"Failed to login! Please Check your credentials",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                }
            }
        });


    }


}
