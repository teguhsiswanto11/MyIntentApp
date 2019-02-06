package com.example.teguhsis.myintentapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MoveActivity extends AppCompatActivity{

    DataHelper myDb;
    EditText editFisrtName;
    EditText editLastName;
    EditText editMarks;
    Button btnAddData;
    Button btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        myDb = new DataHelper(this);

        editFisrtName = findViewById(R.id.edt_FirstName);
        editLastName = findViewById(R.id.edt_LastName);
        editMarks = findViewById(R.id.edt_marks);
        btnAddData = findViewById(R.id.btn_add);
        btnViewAll = findViewById(R.id.btn_viewAll);
        AddData();
        viewAll();

    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                editFisrtName.getText().toString(),
                                editLastName.getText().toString(),
                                editMarks.getText().toString() );
                        if (isInserted = true)
                            Toast.makeText(MoveActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MoveActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    public void viewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myDb.getAllData();
                        if (result.getCount() == 0) {
                            showMessage("Error", "Nothing Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()) {
                            buffer.append("ID : " + result.getString(0) + "\n");
                            buffer.append("First Name : " + result.getString(1) + "\n");
                            buffer.append("Last Name : " + result.getString(2) + "\n");
                            buffer.append("Marks : " + result.getString(3) + "\n\n");
                        }
                    //  show all data
                        showMessage("Data", buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
