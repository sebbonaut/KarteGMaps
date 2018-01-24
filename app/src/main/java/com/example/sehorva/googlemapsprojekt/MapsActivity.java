package com.example.sehorva.googlemapsprojekt;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final LatLng AMFITEATAR =
            new LatLng(44.873222, 13.850155);
    private static final LatLng ZAGREB =
            new LatLng(43.5160411, 16.4140641);
    private static final LatLng SPLIT =
            new LatLng(45.8402941, 15.894292);
    private static final LatLng RIJEKA =
            new LatLng(45.3476649, 14.3917947);
    private static final LatLng OSIJEK =
            new LatLng(45.5463866, 18.6538395);

    private int MY_PERM=1;
    private static final LatLng BUJE =
            new LatLng(45.413944,13.665951);

    double m = 0;
    LatLng prvi;
    LatLng drugi;


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_sethybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.menu_showtraffic:
                mMap.setTrafficEnabled(true);
                break;
            case R.id.menu_zoomin:
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                break;

            case R.id.menu_zoomout:
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
                break;

            case R.id.menu_gotolocation:
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(AMFITEATAR) // Sets the center of the map to
                        // Golden Gate Bridge
                        .zoom(17)                   // Sets the zoom
                        .bearing(90) // Sets the orientation of the camera to east
                        .tilt(30)    // Sets the tilt of the camera to 30 degrees
                        .build();    // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                        cameraPosition));
                break;
            case R.id.menu_addmarker:


                mMap.addMarker(new MarkerOptions()
                        .position(AMFITEATAR)
                        .title("Arena")
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                //.fromResource(R.drawable.pushpin)));
                mMap.addMarker(new MarkerOptions()
                        .position(ZAGREB)
                        .title("Zagreb")
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                //.fromResource(R.drawable.pushpin)));
                mMap.addMarker(new MarkerOptions()
                        .position(SPLIT)
                        .title("Split")
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                //.fromResource(R.drawable.pushpin)));
                mMap.addMarker(new MarkerOptions()
                        .position(RIJEKA)
                        .title("Rijeka")
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                //.fromResource(R.drawable.pushpin)));
                mMap.addMarker(new MarkerOptions()
                        .position(OSIJEK)
                        .title("Osijek")
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                //.fromResource(R.drawable.pushpin)));


                break;

            case R.id.menu_getcurrentlocation:
                // ---get your current location and display a blue dot---
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERM);
                }
                mMap.setMyLocationEnabled(true);

                break;

            case R.id.menu_showcurrentlocation:
                Location myLocation = mMap.getMyLocation();
                LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                        myLocation.getLongitude());

                CameraPosition myPosition = new CameraPosition.Builder()
                        .target(myLatLng).zoom(17).bearing(90).tilt(30).build();
                mMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(myPosition));
                //da ucita kartu na kraju
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                break;

            case R.id.menu_lineconnecttwopoints:
                //---add a marker at Apple---
                /*mMap.addMarker(new MarkerOptions()
                        .position(BUJE)
                        .title("Buje")

                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_AZURE)));*/

                //---draw a line connecting Apple and Golden Gate Bridge---
                //mMap.addPolyline(new PolylineOptions()
                //        .add(AMFITEATAR, BUJE).width(5).color(Color.RED));
                mMap.addPolyline(new PolylineOptions()
                        .add(ZAGREB, SPLIT).width(5).color(Color.RED));
                mMap.addPolyline(new PolylineOptions()
                        .add(ZAGREB, RIJEKA).width(5).color(Color.RED));
                mMap.addPolyline(new PolylineOptions()
                        .add(ZAGREB, OSIJEK).width(5).color(Color.RED));
                mMap.addPolyline(new PolylineOptions()
                        .add(SPLIT, RIJEKA).width(5).color(Color.RED));
                mMap.addPolyline(new PolylineOptions()
                        .add(SPLIT, OSIJEK).width(5).color(Color.RED));
                mMap.addPolyline(new PolylineOptions()
                        .add(RIJEKA, OSIJEK).width(5).color(Color.RED));



                distance(ZAGREB, SPLIT);
                distance(ZAGREB, RIJEKA);
                distance(ZAGREB, OSIJEK);
                distance(SPLIT, RIJEKA);
                distance(SPLIT, OSIJEK);
                distance(RIJEKA, OSIJEK);


                Toast.makeText(this, "Najveca udaljenost: " + m + ", izmedju: " + prvi
                        + "-" + drugi, Toast.LENGTH_LONG).show();
                break;


        }
        return true;
    }

    public void distance (LatLng lt1, LatLng lt2 )
    {
        double lat_a = lt1.latitude;
        double lng_a = lt1.longitude;
        double lat_b = lt2.latitude;
        double lng_b = lt2.longitude;
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b-lat_a);
        double lngDiff = Math.toRadians(lng_b-lng_a);
        double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                        Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;

        int meterConversion = 1609;

        double udaljenost = new Double(distance * meterConversion).floatValue();

        if(udaljenost > m)
        {
            m = udaljenost;
            prvi = lt1;
            drugi = lt2;
        }

        return;
    }

}
