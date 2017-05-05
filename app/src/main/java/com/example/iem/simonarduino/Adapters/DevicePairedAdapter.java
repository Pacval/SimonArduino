package com.example.iem.simonarduino.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iem.simonarduino.Models.Device;
import com.example.iem.simonarduino.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iem on 04/05/2017.
 */

public class DevicePairedAdapter extends BaseAdapter {

    private List<Device> deviceList = new ArrayList<>();
    private Activity activity;

    public DevicePairedAdapter(List<Device> deviceList, Activity activity) {
        this.deviceList = deviceList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return deviceList.size();
    }

    @Override
    public Object getItem(int i) {
        return deviceList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Device device = deviceList.get(i);

        LinearLayout layout;

        if (view == null) {
            layout = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.item_device, null);
        } else {
            layout = (LinearLayout) view;
        }

        final TextView tvNom = (TextView) layout.findViewById(R.id.item_device_tv_nom);
        final TextView tvAddress = (TextView) layout.findViewById(R.id.item_device_tv_address);
        tvNom.setText(device.getName());
        tvAddress.setText(device.getAddress());

        return layout;
    }
}
