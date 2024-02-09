package com.example.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText Username, Password;
    Button Login, Register, changeL, Update;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Login = findViewById(R.id.Login);
        Register = findViewById(R.id.Register);
        changeL = findViewById(R.id.changeL);
        Update = findViewById(R.id.Update);
        DB = new DBHelper(this);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(getResources().getString(R.string.app_name));

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View LGN) {
                String user = Username.getText().toString();
                String pass = Password.getText().toString();

                if(user.equals("")||pass.equals(""))
                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(a);
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View RGR) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);


            }
        });

        changeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View CL) {
                showchangeLDialog();
//                Toast.makeText(MainActivity.this, "Change of Lanaguage Successful", Toast.LENGTH_SHORT).show();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View U) {
                Intent u = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(u);
            }
        });
    }

    private void showchangeLDialog() {
        final String[] lang_list = {"मराठी", "हिंदी","English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select your language preference: ");
        builder.setSingleChoiceItems(lang_list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0) {
                    setLocale("mr");
                    recreate();
                }
                else if(i==1){
                    setLocale("hi");
                    recreate();
                }
                else if(i==2) {
                    setLocale("en");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

//      save shared preferences settings
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My lang", lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My lang", "");
        setLocale(language);
    }

}

