package com.example.project;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Registration extends AppCompatActivity {

    EditText username, Email, password, Phone;
    Button Reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.registration);

        username = findViewById(R.id.username);
        Email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        Phone = findViewById(R.id.Phone);
        Reg = findViewById(R.id.Reg);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(getResources().getString(R.string.app_name));

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper MyDB= new DBHelper(Registration.this);
                MyDB.insertuser(username.getText().toString(),Email.getText().toString(),password.getText().toString(),Phone.getText().toString());
            }
        });

    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My lang", "");
        setLocale(language);
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

//      save shared preferences settings
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My lang", language);
        editor.apply();
    }
}
