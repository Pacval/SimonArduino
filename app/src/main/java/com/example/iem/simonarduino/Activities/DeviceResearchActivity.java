package com.example.iem.simonarduino.Activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.iem.simonarduino.Models.Device;
import com.example.iem.simonarduino.Adapters.DevicePairedAdapter;
import com.example.iem.simonarduino.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeviceResearchActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter = null;
    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_device);

        final Button btSearch = (Button) findViewById(R.id.act_deviceres_bt_search);
        final ListView lvDevices = (ListView) findViewById(R.id.act_deviceres_lv_devices);

        final List<Device> pairedDevicesList = new ArrayList<>();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(DeviceResearchActivity.this, "Le bluetooth n'est pas disponible sur cet appareil", Toast.LENGTH_LONG).show();
            finish();
        } else if (!bluetoothAdapter.isEnabled()) {
            Intent intentTurnOnBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intentTurnOnBluetooth, 1);
        }

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pairedDevicesList.clear();
                pairedDevices = bluetoothAdapter.getBondedDevices();

                if (pairedDevices.size() > 0) {
                    for (BluetoothDevice bluetoothDevice : pairedDevices) {
                        pairedDevicesList.add(new Device(bluetoothDevice.getName(), bluetoothDevice.getAddress()));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
                }

                DevicePairedAdapter devicePairedAdapter = new DevicePairedAdapter(pairedDevicesList, DeviceResearchActivity.this);
                lvDevices.setAdapter(devicePairedAdapter);
            }
        });

        lvDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DeviceResearchActivity.this, GameActivity.class);
                intent.putExtra("device_address", pairedDevicesList.get(i).getAddress());
                startActivity(intent);
            }
        });

    }
}
