package org.pimatic.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h3llfire on 21.06.14.
 */
public class Group {

    private String name;
    private String id;
    private ArrayList<String> deviceIds = new ArrayList<>();

    public Group(JSONObject obj) throws JSONException {
        this.id = obj.getString("id");
        this.name = obj.getString("name");
        JSONArray deviceIdsArray = obj.getJSONArray("devices");
        for (int i = 0; i < deviceIdsArray.length(); i++) {
            String deviceId = deviceIdsArray.getString(i);
            deviceIds.add(deviceId);
        }
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public List<Device> getDevices() {
        ArrayList<Device> devices = new ArrayList<>();
        for (int i = 0; i < deviceIds.size(); i++) {
            String deviceId = deviceIds.get(i);
            Device d = DeviceManager.getDeviceById(deviceId);
            if (d == null) {
                Log.w("Group", "Could not find device: " + deviceId);
            } else {
                devices.add(d);
            }
        }
        return devices;
    }
}
