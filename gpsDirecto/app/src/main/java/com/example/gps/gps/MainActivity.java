package com.example.gps.gps;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity implements LocationListener {
    private TextView salida, no;
    private EditText nombreT;
    private Button bregistrar, bstart, bstop, bcapturas, bbajo,balto;
    private LocationManager mLocationManager;/////////////////////////////////////////////////
    private String nombre = "";
    private bdManager bd;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salida = (TextView) findViewById(R.id.textView2);
        salida.setText("");
        nombreT = (EditText) findViewById(R.id.editText);
        no = (TextView) findViewById(R.id.textView3);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        bregistrar = (Button) findViewById(R.id.button4);
        bstart = (Button) findViewById(R.id.button);
        bstop = (Button) findViewById(R.id.button2);
        bcapturas = (Button) findViewById(R.id.button3);
        bbajo = (Button)findViewById(R.id.button5);
        balto= (Button)findViewById(R.id.button6);
        bd = new bdManager(this);
        cursor = bd.cargardb();
        if (cursor.getCount() > 0) {

            String[] from = new String[]{bd.CN_NAME};

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void registrar(View view) {
        nombre = nombreT.getText().toString();
        nombreT.setVisibility(View.GONE);
        no.setVisibility(View.GONE);
        bregistrar.setVisibility(View.GONE);
        bstart.setVisibility(View.VISIBLE);
        bstop.setVisibility(View.VISIBLE);
        bcapturas.setVisibility(View.VISIBLE);
        bbajo.setVisibility(view.VISIBLE);
        balto.setVisibility(View.VISIBLE);
    }

    public void capturas(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        salida.setText("Latitud : " + location.getLatitude() + "\r\n Longitud : " + location.getLongitude() + "");
        bd.insertarvalor(nombre, "" + location.getLatitude(), "" + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void StartGPS(View view) {

        //Criteria req = new Criteria();
        //req.setAccuracy(Criteria.ACCURACY_LOW);
        //req.setPowerRequirement(Criteria.POWER_LOW);
        //String provider = mLocationManager.getBestProvider(req,true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);////////////////////////////////////////////

    }

/*
    String LOW() {
        LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_LOW); // Chose your desired power consumption level.
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // Choose your accuracy requirement.


        // Provide your criteria and flag enabledOnly that tells
        // LocationManager only to return active providers.
        return locationManager.getBestProvider(criteria, true);
    }



    public void StartGPSLOW(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);////////////////////////////////////////////
            LOW();
    }
*/


    public void StopGPS(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        mLocationManager.removeUpdates(this);///////////////////////////////////////////////////////////
        salida.setText("");
        nombre="";
        nombreT.setVisibility(View.VISIBLE);
        no.setVisibility(View.VISIBLE);
        bregistrar.setVisibility(View.VISIBLE);
        bstart.setVisibility(View.GONE);
        bstop.setVisibility(View.GONE);
        bcapturas.setVisibility(View.GONE);
        balto.setVisibility(View.GONE);
        bbajo.setVisibility(View.GONE);
    }
}
