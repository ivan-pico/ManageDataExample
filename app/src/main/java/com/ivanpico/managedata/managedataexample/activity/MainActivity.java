package com.ivanpico.managedata.managedataexample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ivanpico.managedata.managedataexample.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import controller.MainController;
import entity.ErrorEvent;
import entity.OpenRate;

    /*
    * API server https://openexchangerates.org
    * pablo@mailinator.com
    * pabloopenexchangerates
    * Api key fb478696e5d1403ab4c356be6e4dea8f
    * */

public class MainActivity extends BaseActivity {

    private TextView textView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout to Activity
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Send request
                MainController.getUSDRates();
            }
        });
    }

    // SUSCRIBERS
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OpenRate openRateResult) {

        // Show result to Textview
        textView.setText(openRateResult.getRates().toString());



    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ErrorEvent openRateResult) {
        // Whatever error interface
        Toast.makeText(getApplicationContext(),"Server error, try again." ,Toast.LENGTH_LONG).show();
    };



}
