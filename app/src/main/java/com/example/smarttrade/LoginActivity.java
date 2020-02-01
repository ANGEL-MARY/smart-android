package com.example.smarttrade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.mapbox.api.geocoding.v5.GeocodingCriteria;
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.plugins.places.picker.PlacePicker;
import com.mapbox.mapboxsdk.plugins.places.picker.model.PlacePickerOptions;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton loginButton;
    private EditText nameEditText, phonEditText;

    private static final int REQUEST_CODE = 5678, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=999;
    private double lat =40.7544, lon=-73.9862;
    private boolean mLocationPermissionGranted;

    private String phone, name;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        //Initialize components
        loginButton = findViewById(R.id.loginButton);
        nameEditText = findViewById(R.id.name);
        phonEditText = findViewById(R.id.phone);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To read use getText()
                name = nameEditText.getText().toString();
                phone = phonEditText.getText().toString();

                if(name.length() < 3){
                    nameEditText.requestFocus();
                    nameEditText.setError("Please enter your name");
                }else if(phone.length() < 1){
                    nameEditText.setError(null);
                    phonEditText.requestFocus();
                    phonEditText.setError("Please enter a phone number");
                }else if(phone.length() < 10){
                    nameEditText.setError(null);
                    phonEditText.requestFocus();
                    phonEditText.setError("Please enter a valid phone number");

                }
                else {
                    phonEditText.setError(null);
                    Toast.makeText(getApplicationContext(), "Name : " + name, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Phone : " + phone, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplication(), OtpVerificationActivity.class)
                            .putExtra("phone", phone));
                    finish();
                }
//               goToPickerActivity();

            }
        });

        getLocationPermission();
        getDeviceLocation();
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
            }
        }
    }

}