package com.ivanpico.managedata.managedataexample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ivanpico.managedata.managedataexample.R;

import controller.MainController;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    /*
    * API server https://openexchangerates.org
    * pablo@mailinator.com
    * pabloopenexchangerates
    * Api key fb478696e5d1403ab4c356be6e4dea8f
    * */

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

}
