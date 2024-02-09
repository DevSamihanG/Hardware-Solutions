package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    CheckBox enable, visible;
    ImageView bt_search;
    ListView bt_devices;
    TextView bt_name;
    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        enable = findViewById(R.id.enable);
        visible = findViewById(R.id.visible);
        bt_search = findViewById(R.id.bt_search);
        bt_name = findViewById(R.id.bt_name);
        bt_devices = findViewById(R.id.bt_devices);

        //get your bluetooth device
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter==null){
            Toast.makeText(this, "Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(bluetoothAdapter.isEnabled()){
            enable.setChecked(true);
        }

        enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!bluetoothAdapter.isEnabled()){
                    Intent bt_enable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(bt_enable,0);
                    Toast.makeText(BluetoothActivity.this, "Bluetooth is now Enabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        visible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Intent bt_visible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(bt_visible,0);
                Toast.makeText(BluetoothActivity.this, "Bluetooth is now Discoverable", Toast.LENGTH_SHORT).show();
            }
        });

        bt_search.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                pairedDevices = bluetoothAdapter.getBondedDevices();
                ArrayList devices = new ArrayList();

                for(BluetoothDevice bt: pairedDevices){
                    devices.add(bt.getName());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, devices);

                bt_devices.setAdapter(arrayAdapter);
                bt_name.setText(bluetoothAdapter.getName());
            }
        });

    }
}



