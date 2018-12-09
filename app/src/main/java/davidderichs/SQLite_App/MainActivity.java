package davidderichs.SQLite_App;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ramotion.fluidslider.FluidSlider;

import kotlin.Unit;

import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FeedReaderContract mFeedReaderContract;

    Button insertEntry_button;
    Button showEntry_button;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mFeedReaderContract = new FeedReaderContract(this);
        this.mFeedReaderContract.deleteAllEntries();

        this.insertEntry_button = findViewById(R.id.insertEntryButton);
        this.insertEntry_button.setOnClickListener(this);

        this.showEntry_button = findViewById(R.id.showEntryButton);
        this.showEntry_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insertEntryButton:
                InsertEntry();
                break;
            case R.id.showEntryButton:
                ShowEntries();
                break;
        }
    }

    public void InsertEntry(){
        Log.d("MainActivity", "InsertEntry");
        EditText name = (EditText) findViewById(R.id.textView_Name);
        EditText nachname = (EditText) findViewById(R.id.textView_Nachame);
        EditText spitzname = (EditText) findViewById(R.id.textView_Spitzname);
        EditText telefonnummer = (EditText) findViewById(R.id.textView_Telefonnummer);

        mFeedReaderContract.InsertEntry(name.getText().toString(), nachname.getText().toString(), spitzname.getText().toString(), telefonnummer.getText().toString());
        name.setText("");
        nachname.setText("");
        spitzname.setText("");
        telefonnummer.setText("");
    }

    public void ShowEntries(){

        try {
            Cursor cursor = mFeedReaderContract.ReadEntry();
            int i;
            String out = "";
            if(cursor.getCount() <= 0)
                return;
            cursor.moveToFirst();
            for (i = 0; i < cursor.getCount(); i++) {
                out = out + cursor.getString(cursor.getColumnIndex("Name")) + " "
                        + cursor.getString(cursor.getColumnIndex("Nachname")) + " "
                        + cursor.getString(cursor.getColumnIndex("Spitzname")) + " "
                        + cursor.getString(cursor.getColumnIndex("Telefonnummber")) + "\n";
                cursor.moveToNext();
            }
            TextView table = (TextView) findViewById(R.id.textViewOutput2);
            table.setText(String.format("%s", out));
        } catch (Exception e){
            Log.d("MainActivity", e.getMessage());
        }

    }




}
