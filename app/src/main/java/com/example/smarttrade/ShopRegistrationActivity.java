package com.example.smarttrade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.config.Session;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Buyer;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.plugins.places.picker.PlacePicker;
import com.mapbox.mapboxsdk.plugins.places.picker.model.PlacePickerOptions;

import java.util.HashMap;

public class ShopRegistrationActivity extends AppCompatActivity {

    private MaterialButton continueButton, pickLocationButton;
    private EditText shopnameEditText, storetypeEditText, locationEditText;


    private String shopname, storetype, location;
    private static final int REQUEST_CODE = 5678, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=999;
    private double lat = 0, lon=0;
    private boolean mLocationPermissionGranted;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;


    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_registration);


        continueButton = findViewById(R.id.shopSave);
        shopnameEditText = findViewById(R.id.shopName);
        storetypeEditText = findViewById(R.id.storeType);
        locationEditText = findViewById(R.id.shopAddress);
        pickLocationButton = findViewById(R.id.pick_buyer_location);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocationPermission();
        getDeviceLocation();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //To read use getText()
                shopname = shopnameEditText.getText().toString();
                storetype = storetypeEditText.getText().toString();
                location = locationEditText.getText().toString();

                if(shopname.length() == 0){

                    shopnameEditText.setError("Shop name is required");
                    shopnameEditText.findFocus();
                }
                else if(storetype.length() == 0){

                    storetypeEditText.setError("Store type is required");
                    storetypeEditText.findFocus();
                }
                else if(location.length() == 0){

                    locationEditText.setError("Address is required");
                    locationEditText.findFocus();
                }
                else if(lat == 0&& lon ==0 )
                    Toast.makeText(getApplicationContext(), "Please pick a loc", Toast.LENGTH_SHORT).show();

                if(shopname.length() == 0 && storetype.length() == 0 && location.length() == 0 && lat > 0  )
                    Toast.makeText(getApplicationContext(), "Nice workPlease pick a loc", Toast.LENGTH_SHORT).show();

                    DataManager.getDataManager().buyerRegistration(getbuyerparams(shopname, storetype, location, lat, lon), new RetrofitCallBack<Buyer>() {
                        @Override
                        public void Success(Buyer data) {
                            startActivity(new Intent(ShopRegistrationActivity.this, MainActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        }

                        @Override
                        public void Failure(String error) {

                        }
                    });
            }
        });

        pickLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToPickerActivity();
            }
        });
    }
    private HashMap<String, String> getbuyerparams(String shopname, String storetype, String location, double latitude, double longitude){

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("shopname",shopname);
        hashMap.put("storetype", storetype);
        hashMap.put("address", location);
        hashMap.put("latitude", latitude+"");
        hashMap.put("longitude", longitude+"");


        return hashMap;
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            if (mLastKnownLocation != null) {
                                lat = mLastKnownLocation.getLatitude();
                                lon = mLastKnownLocation.getLongitude();
//                                Toast.makeText(getApplicationContext(), lon+ "", Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }




    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }


    /**
     * Set up the PlacePickerOptions and startActivityForResult
     */
    private void goToPickerActivity() {
        startActivityForResult(
                new PlacePicker.IntentBuilder()
                        .accessToken(getString(R.string.mapbox_access_token))
                        .placeOptions(PlacePickerOptions.builder()
                                .statingCameraPosition(new CameraPosition.Builder()
                                        .target(new LatLng(lat, lon)).zoom(16).build())
                                .build())
                        .build(this), REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // Retrieve the information from the selected location's CarmenFeature
            CarmenFeature carmenFeature = PlacePicker.getPlace(data);

            // Set the TextView text to the entire CarmenFeature. The CarmenFeature
            // also be parsed through to grab and display certain information such as
            // its placeName, text, or coordinates.
            if (carmenFeature != null) {
//                (String.format(getString(R.string.selected_place_info), carmenFeature.toJson()));
                Log.d("Location",   carmenFeature.toJson().toString());

                lat = carmenFeature.center().latitude();
                lon  = carmenFeature.center().longitude();
            }
        }
    }
}
