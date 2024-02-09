package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Gmail extends AppCompatActivity {

    EditText subject, body;

    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmail);

        subject = findViewById(R.id.subject);
        body = findViewById(R.id.body);
        send = findViewById(R.id.send);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(getResources().getString(R.string.app_name));


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View M) {
                Intent gmail = new Intent(Intent.ACTION_SEND);
                gmail.setType("text/html");
                gmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"samihansg@gmail.com"});
                gmail.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                gmail.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
                startActivity(gmail);
            }
        });
    }
}
