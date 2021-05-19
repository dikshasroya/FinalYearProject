package com.example.shopmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VirtualQueue extends AppCompatActivity implements View.OnClickListener {

    private Button AddQueue,LeaveQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_queue);

        AddQueue=(Button) findViewById(R.id.addQueue);
        AddQueue.setOnClickListener(this);

        LeaveQueue=(Button) findViewById(R.id.leaveQueue);
        LeaveQueue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.addQueue:
                startActivity(new Intent(this,AddQueue.class));
                break;
            case R.id.leaveQueue:
                startActivity(new Intent(this,LeaveQueue.class));
                break;

        }
    }
}