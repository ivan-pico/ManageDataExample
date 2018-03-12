package controller;

import entity.OpenRate;
import network.RateService;
import network.RetrofitController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.GlobalConstant;

/**
 * Created by ipicomar on 12/03/2018.
 */

public class MainController {

    public static void getUSDRates() {

        Retrofit retrofit = RetrofitController.getRetrofit();

        RateService service = retrofit.create(RateService.class);
        Call<OpenRate> callAsync = service.getUSDRates("fb478696e5d1403ab4c356be6e4dea8f", "USD");

        callAsync.enqueue(new Callback<OpenRate>() {
            @Override
            public void onResponse(Call<OpenRate> call, Response<OpenRate> response) {
                OpenRate openRate = response.body();

                GlobalConstant.glLog("Open Rate result " + openRate);
            }

            @Override
            public void onFailure(Call<OpenRate> call, Throwable throwable) {

                GlobalConstant.glLog("ERROR Open Rate result ");
            }
        });
    }
}
