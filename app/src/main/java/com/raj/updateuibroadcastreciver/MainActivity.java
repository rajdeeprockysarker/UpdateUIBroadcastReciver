package com.raj.updateuibroadcastreciver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview=(TextView)findViewById(R.id.textview);
    }
    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyService.MY_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
        //Start our own service
        Intent intent = new Intent(MainActivity.this,
                MyService.class);
        startService(intent);
        super.onStart();
    }
    /*@Override
    protected void onStop() {
        // TODO Auto-generated method stub
        unregisterReceiver(myReceiver);
        super.onStop();
    }*/
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int datapassed = intent.getIntExtra("DATAPASSED", 0);
            String s = intent.getAction().toString();
            String s1 = intent.getStringExtra("DATAPASSED");
            textview.setText(s1);
            Toast.makeText(context,
                    "Triggered by Service!\n"
                            + "Data passed: " + String.valueOf(s1),
                    Toast.LENGTH_LONG).show();
        }
    };
}