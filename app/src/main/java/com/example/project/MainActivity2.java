package com.example.project;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.product);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(getResources().getString(R.string.app_name));

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        List<Product.Item> Product = new ArrayList<Product.Item>();
        Product.add(new Product.Item(getString(R.string.Plier), getString(R.string.Text), R.drawable.plier));
        Product.add(new Product.Item(getString(R.string.Screwdriver), getString(R.string.Text1), R.drawable.screwdriver));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), Product));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                showchangeLDialog();
//                Toast.makeText(this, "Change Language", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item1b:
                Intent Bluetooth = new Intent(getApplicationContext(), BluetoothActivity.class);
                startActivity(Bluetooth);
//                Toast.makeText(this, "Bluetooth started", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item2:
                Intent Email = new Intent(getApplicationContext(), Gmail.class);
                startActivity(Email);
                return true;

            case R.id.item3:
                Intent Logout = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Logout);
//                Toast.makeText(this, "Logging off", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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



