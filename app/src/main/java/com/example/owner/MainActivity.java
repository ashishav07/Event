package com.example.owner.event;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<view> fields=new ArrayList<view>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton b= findViewById(R.id.fab);
        //sample starts
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentform=new Intent(MainActivity.this,FormActivity.class);
                startActivity(intentform);
            }
        });
        Intent intent = getIntent();
        String eName= intent.getStringExtra("Name");
        String eDate= intent.getStringExtra("Date");
        String eTime= intent.getStringExtra("Time");
        fields.add(new view(eName,eDate,eTime));
        viewAdapter v1=new viewAdapter(this,fields);
        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(v1);
    }
}
