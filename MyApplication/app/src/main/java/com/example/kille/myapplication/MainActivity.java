package com.example.kille.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView txt1,txt2,txt3,txt4;
    SeekBar seek1,seek2,seek3,seek4;
    Button reset, connect;
    int servo1,servo2,servo3,servo4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase myDb = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = myDb.getReference();

        txt1 = (TextView)(findViewById(R.id.textView));
        txt2 = (TextView)(findViewById(R.id.textView2));
        txt3 = (TextView)(findViewById(R.id.textView3));
        txt4 = (TextView)(findViewById(R.id.textView4));

        seek1 = (SeekBar) (findViewById(R.id.seekBar));
        seek2 = (SeekBar)(findViewById(R.id.seekBar2));
        seek3 = (SeekBar)(findViewById(R.id.seekBar3));
        seek4 = (SeekBar)(findViewById(R.id.seekBar4));

        reset = (Button)(findViewById(R.id.button));
        connect = (Button)(findViewById(R.id.Connect));

        final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                servo1=progress;
                txt1.setText(String.valueOf(progress));
                try {
                    myRef.child("Motors").child("Claw").setValue(progress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                servo2=progress;
                txt2.setText(String.valueOf(progress));
                try {
                    myRef.child("Motors").child("Elbow").setValue(progress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                servo3=progress;
                txt3.setText(String.valueOf(progress));
                try {
                    myRef.child("Motors").child("Arm").setValue(progress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seek4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                servo4=progress;
                txt4.setText(String.valueOf(progress));
                try {
                    myRef.child("Motors").child("Base").setValue(progress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                seek1.setProgress(50);
                seek2.setProgress(50);
                seek3.setProgress(50);
                seek4.setProgress(50);
            }
        });
        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (adapter == null)
                    try {
                        throw new Exception("No Bluetooth adapter found.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                if (!adapter.isEnabled())
                    try {
                        throw new Exception("Bluetooth adapter is not enabled.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    
            }
        });

    }
}

