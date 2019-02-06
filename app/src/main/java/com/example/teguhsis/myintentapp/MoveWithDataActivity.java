package com.example.teguhsis.myintentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MoveWithDataActivity extends AppCompatActivity {
    public static final String EXTRA_NAMA = "nama";
    public static final String EXTRA_USIA = "usia";

    TextView tvDataReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_data);

        tvDataReceived = (TextView) findViewById(R.id.tv_data_received);

        String name = getIntent().getStringExtra(EXTRA_NAMA);
        int age = getIntent().getIntExtra(EXTRA_USIA, 0);


        String text = "Name : " + name + "\nYour Age : " + age;
        tvDataReceived.setText(text);
    }
}
