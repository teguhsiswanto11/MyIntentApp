package com.example.teguhsis.myintentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MoveForResultActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnChoose;
    RadioGroup rgNumber;

    public static String EXTRA_SELECTED_VALUE = "extra_selected_value";
    public static int RESULT_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_for_result);

        btnChoose = findViewById(R.id.btn_choose);
        rgNumber = findViewById(R.id.rg_choose_number);

        btnChoose.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //jika tombol btn_choose dipilih
        if (view.getId() == R.id.btn_choose) {
            // jika radio button tidak ada yang kosong / ada yang dipilih
            if (rgNumber.getCheckedRadioButtonId() != 0) {
                int value = 0;
                switch (rgNumber.getCheckedRadioButtonId()) {
                    case R.id.rb_7:
                        value = 7;
                        break;
                    case R.id.rb_10:
                        value = 10;
                        break;
                    case R.id.rb_11:
                        value = 11;
                        break;
                    case R.id.rb_87:
                        value = 87;
                        break;
                    case R.id.rb_90:
                        value = 90;
                        break;
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value);
                setResult(RESULT_CODE, resultIntent);
                finish();

            }

        }
    }
}
