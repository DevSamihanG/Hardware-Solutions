package com.example.project;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {

    EditText editText1, sphone;
    TextView output;

    Button Search, Showall;
    DBHelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        sphone = findViewById(R.id.sphone);
        Search = findViewById(R.id.Search);
        Showall = findViewById(R.id.Showall);
        output=findViewById(R.id.textView5);
        databasehelper=new DBHelper(this);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder result = new StringBuilder();
                String user ;
                String Email;
                String Password;
                String Phone=sphone.getText().toString();

                Cursor res = databasehelper.retrieve(Phone);
                while(res.moveToNext()){
                    user="Username:"+res.getString(1);
                    Email="\nEmail:"+res.getString(2);
                    Password="\nPassword:"+res.getString(3);
                    Phone="\nPhone:"+res.getString(4)+"\n\n";

                    result.append(user);
                    result.append(Email);
                    result.append(Password);
                    result.append(Phone);
                    output.setText(result);
                }
            }
        });

        Showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder result = new StringBuilder();
                String user ;
                String Email;
                String Password;
                String Phone;

                Cursor res = databasehelper.showall();
                while(res.moveToNext()){
                    user="Username:"+res.getString(1);
                    Email="\nEmail:"+res.getString(2);
                    Password="\nPassword:"+res.getString(3);
                    Phone="\nPhone:"+res.getString(4)+"\n\n";

                    result.append(user);
                    result.append(Email);
                    result.append(Password);
                    result.append(Phone);
                    output.setText(result);
                    output.setMovementMethod(new ScrollingMovementMethod());
                }
            }
        });
    }
}
