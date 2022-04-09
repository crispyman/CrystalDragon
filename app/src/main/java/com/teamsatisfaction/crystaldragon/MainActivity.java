package com.teamsatisfaction.crystaldragon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // refference to buttons (member varables)
    Button btn_add;
    EditText et_stringData, et_intData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.button1);
        et_stringData = findViewById(R.id.et_stringData);
        et_intData = findViewById(R.id.et_intData);

        // button listener
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText( context: MainActivity.this, text: "Add button", Toast.LENGTH_SHORT).show();

                database data;

                try {
                     data = new database(et_stringData.getText().toString(), Integer.parseInt(et_intData.getText().toString()));
                    Toast.makeText(MainActivity.this, data.toString() , Toast.LENGTH_SHORT).show();

                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error, please enter valid data" , Toast.LENGTH_SHORT).show();
                    data = new database("invalid data", -1);

                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                databaseHelper.addOne(data);



            }
        });




    }
}