package com.example.gps.gps;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity2Activity extends ActionBarActivity {
    private TextView salidaN,salidaL,salidaLo;
    private  bdManager bd;
    Cursor cursor;
    private String nombre="",lat="",lon="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        salidaN = (TextView) findViewById(R.id.textView7);
        salidaL = (TextView) findViewById(R.id.textView8);
        salidaLo = (TextView) findViewById(R.id.textView9);
        Intent intent = getIntent();
        bd = new bdManager(this);
        cursor = bd.cargardb();
        if(cursor.getCount()>0) {

            if (cursor.moveToFirst()) {

                do {

                    nombre=nombre+cursor.getString(1)+" \r\n";
                    lat=lat+cursor.getString(2)+" \r\n";
                    lon=lon+cursor.getString(3)+"\r\n";


                } while(cursor.moveToNext());
            }
            salidaN.setText(""+nombre);
            salidaL.setText(""+lat);
            salidaLo.setText(""+lon);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
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
}
