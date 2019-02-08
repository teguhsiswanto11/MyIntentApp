package com.example.teguhsis.myintentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teguhsis.myintentapp.data.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnMoveActivity;
    private Button btnMoveWithDataActivity;
    private Button btnDialPhone;
    private Button btnMoveWithObjectActivity;
    private Button btnMoveForResult;
    private EditText edtName;
    private EditText edtAge;
//   variables for data Object
    private EditText edtYourName;
    private EditText edtYourAge;
    private EditText edtYourEmail;
    private EditText edtYourCity;
    private TextView tvResult;

    private int REQUEST_CODE = 100;

    DataHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataHelper(this);

        btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        btnMoveWithDataActivity = (Button) findViewById(R.id.btn_move_with_data);
        btnMoveWithDataActivity.setOnClickListener(this);

        btnMoveWithObjectActivity = (Button) findViewById(R.id.btn_move_with_object);
        btnMoveWithObjectActivity.setOnClickListener(this);

        btnDialPhone = (Button) findViewById(R.id.btn_dial_number);
        btnDialPhone.setOnClickListener(this);

        btnMoveForResult = findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);

        // read input text
        edtName = findViewById(R.id.edt_name);
        edtAge = findViewById(R.id.edt_age);
        // read input parcelable object
        edtYourName = findViewById(R.id.edt_yourname);
        edtYourAge = findViewById(R.id.edt_yourage);
        edtYourEmail = findViewById(R.id.edt_email);
        edtYourCity = findViewById(R.id.edt_city);

        // result from Activity MoveForResult
        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_move_activity:
                Intent moveActivity = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveActivity);
                break;

            case R.id.btn_move_with_data:
                Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                // get input from EditText
                String name = edtName.getText().toString();

                Integer usia = 0;
                try {
                    usia = Integer.parseInt(edtAge.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Usia tidak valid", Toast.LENGTH_SHORT).show();
                }

                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAMA, name);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_USIA, usia);
                startActivity(moveWithDataIntent);
                break;

            case R.id.btn_move_with_object:

                String ynama  = edtYourName.getText().toString();
                String yemail = edtYourEmail.getText().toString();
                String ycity  = edtYourCity.getText().toString();

                Person person = new Person();

                Integer umur = 0;
                try {
                    umur = Integer.parseInt(edtYourAge.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Usia tidak valid", Toast.LENGTH_SHORT).show();
                }

                person.setName(ynama);
                person.setAge(umur);
                person.setEmail(yemail);
                person.setCity(ycity);

                Intent moveWithObjectIntent = new Intent(MainActivity.this, MoveWithObjectActivity.class);
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person);
                startActivity(moveWithObjectIntent);
                // Toast.makeText(MainActivity.this, "Object Intent Belum Dibuat", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_dial_number:
                String phoneNumber = "08996976185";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dialPhoneIntent);
                break;

            case R.id.btn_move_for_result:
                Intent moveForResult = new Intent(MainActivity.this, MoveForResultActivity.class);
                startActivityForResult(moveForResult, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE) {
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s", selectedValue));

            }
        }

    }
}
