package com.example.viewmoelexaple.ThreadExample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.viewmoelexaple.R;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private static  final String TAG = MainActivity.class.getSimpleName();

    private TextView counttxt;
    private Button btnstart,stopbtn;
    MainActivityViewModel mainActivityViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        updateCounter();
    }


    private  void init(){

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        counttxt = findViewById(R.id.counttxt);
        btnstart = findViewById(R.id.btnstart);
        stopbtn = findViewById(R.id.stopbtn);
        btnstart.setOnClickListener(this);
        stopbtn.setOnClickListener(this);

    }


    private void updateCounter(){

        mainActivityViewModel.getCounter().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                counttxt.setText("Counter : "+integer);

                Log.d(TAG, "onChanged: "+integer);
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btnstart:
                mainActivityViewModel.startCounter();
                break;
            case R.id.stopbtn:
                mainActivityViewModel.stopcounter();
                break;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
