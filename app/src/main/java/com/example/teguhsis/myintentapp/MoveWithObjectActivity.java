package com.example.teguhsis.myintentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.teguhsis.myintentapp.data.Person;

public class MoveWithObjectActivity extends AppCompatActivity {

    public static final String EXTRA_PERSON = "extra_person";
    TextView tvObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_object);

        // kenalkan TextView-nya
        tvObject = findViewById(R.id.tv_object_received);

        // menerima Object dari Activity asal
        Person person = getIntent().getParcelableExtra(EXTRA_PERSON);
//        Integer usia = person.getAge();
        String text = "Name : " + person.getName() +" \nAge : " + person.getAge() + " \nEmail : " + person.getEmail()
                + " \nCity : " + person.getCity();
        tvObject.setText(text);

    }
}
