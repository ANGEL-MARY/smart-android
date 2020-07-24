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
import com.example.smarttrade.config.Session;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Seller;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.mapbox.api.geocoding.v5.GeocodingCriteria;
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.plugins.places.picker.PlacePicker;
import com.mapbox.mapboxsdk.plugins.places.picker.model.PlacePickerOptions;

import java.util.HashMap;

public class SellerActivity extends AppCompatActivity{

    private MaterialButton continueButton, pickeLocation;
    private EditText sellerdeliverrangeEditText,  sellerAddressEditText;


    private String sellerdeliverrange, sellerAddress;

    private static final int REQUEST_CODE = 5678, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=999;
    private double lat = 0, lon=0;
    private boolean mLocationPermissionGranted;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        continueButton = findViewById(R.id.seller);
        pickeLocation = findViewById(R.id.pick_location);
        sellerdeliverrangeEditText = findViewById(R.id.sellerDeliverRange);
        sellerAddressEditText = findViewById(R.id.seller_address);


        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocationPermission();
        getDeviceLocation();


        pickeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    goToPickerActivity();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                sellerdeliverrange = sellerdeliverrangeEditText.getText().toString();
                sellerAddress = sellerAddressEditText.getText().toString();
                if(sellerAddress.length() == 0){
                    sellerdeliverrangeEditText.findFocus();
                    sellerdeliverrangeEditText.setError("Delivery range is required");
                } else if(sellerAddress.length() == 0){
                    sellerAddressEditText.findFocus();
                    sellerAddressEditText.setError("Address is required");
                } else if(lat == 0&& lon ==0 )
                    Toast.makeText(getApplicationContext(), "Please pick a location", Toast.LENGTH_SHORT).show();

                DataManager.getDataManager().sellerRegistration(getsellerparams(sellerdeliverrange, sellerAddress, lat, lon), new RetrofitCallBack<Seller>() {
                    @Override
                    public void Success(Seller data) {
                        Session.setUserType("seller");
                        startActivity(new Intent(getApplication(), SellerMainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }

                    @Override
                    public void Failure(String error) {

                    }
                });

            }
        });

    }
    private HashMap<String, String> getsellerparams(String sellerdeliverrange, String sellerAddress,double latitude,double longitude){

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("range",sellerdeliverrange);
        hashMap.put("address", sellerAddress);
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
