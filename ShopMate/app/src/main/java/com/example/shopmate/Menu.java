package com.example.shopmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    private Button VirtualQueue,VirtualMirror,Shopping,Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        VirtualQueue=(Button) findViewById(R.id.virtualQueue);
        VirtualQueue.setOnClickListener(this);

        VirtualMirror=(Button) findViewById(R.id.virtualMirror);
        VirtualMirror.setOnClickListener(this);

        Shopping=(Button) findViewById(R.id.shopping);
        Shopping.setOnClickListener(this);

        Logout=(Button) findViewById(R.id.logout);
        Logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.logout:
                Logout();
                break;
            case R.id.shopping:
                startActivity(new Intent(this,Shpping.class));
                break;
            case R.id.virtualQueue:
                startActivity(new Intent(this,VirtualQueue.class));
                break;
        }
    }

    private void Logout() {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Menu.this,MainActivity.class));
    }


}