package com.example.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText username, Email, password, Phone;

    Button UpdateUser, DeleteUser, Usearch;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        username = findViewById(R.id.updateUsername);
        Email = findViewById(R.id.updateEmail);
        password = findViewById(R.id.updatePassword);;
        Phone = findViewById(R.id.updatePhone);
        UpdateUser = findViewById(R.id.UpdateUser);
        DeleteUser = findViewById(R.id.DeleteUser);
        Usearch = findViewById(R.id.Usearch);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(getResources().getString(R.string.app_name));

        DeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        UpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper mDb =new DBHelper(UpdateActivity.this);
                mDb.updateData(id,username.getText().toString(),Email.getText().toString(),password.getText().toString(),Phone.getText().toString());
                Intent intent =new Intent(UpdateActivity.this, MainActivity.class );
                startActivity(intent);
            }
        });
        Usearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sch = new Intent(getApplicationContext(), Search.class);
                startActivity(sch);
            }
        });
//        getIntentData();

    }
//    public  void  getIntentData()
//    {
//        Bundle b= new Bundle();
//        b =getIntent().getExtras();
//        if(b.getString("username") !=null)
//        {
//            id=b.getString("id");
//            username.setText(b.getString("name"));
//            Email.setText(b.getString("email"));
//            password.setText(b.getString("password"));
//            Phone.setText(b.getString("phone"));
//        }
//        else
//        {
//            Toast.makeText(UpdateActivity.this,"No data Found",Toast.LENGTH_LONG).show();
//        }
//    }



    void confirmDialog() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Delete?"   );
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper mydb=new DBHelper(UpdateActivity.this);
                mydb.delete(Phone.getText().toString());
                Intent intent =new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}