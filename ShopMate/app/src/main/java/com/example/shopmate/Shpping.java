package com.example.shopmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

public class Shpping extends AppCompatActivity {

    //Declaration
    //Declaration
    Button btnMove;
    Button help;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shpping);
        //Initialization and Instantiation
        FirebaseApp.initializeApp(this);

        btnMove=(Button) findViewById(R.id.ok);
        help=(Button) findViewById(R.id.help);
        username=(TextView) findViewById(R.id.username);



        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().length()==0){
                    Toast.makeText(Shpping.this,"Enter Cart Number...",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String input = username.getText().toString();
                    Intent i = new Intent(Shpping.this,Bill.class); //To proceed to bill calculation activity
                    i.putExtra("input",input);
                    startActivity(i);
                }
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Shpping.this,chatbot.class); //To proceed to chatbot for help
                startActivity(intent);
            }
        });
    }
    }


