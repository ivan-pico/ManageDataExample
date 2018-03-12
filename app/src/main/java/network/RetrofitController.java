package network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.GlobalConstant;

/**
 * Created by ipicomar on 12/03/2018.
 */

public class RetrofitController {

    private static RetrofitController retrofitController;
    private static Retrofit retrofit;
    private static OkHttpClient.Builder httpClient;


    public static RetrofitController getInstance() {
        if (retrofitController == null) {
            retrofitController = new RetrofitController();

            httpClient = new OkHttpClient.Builder();
            retrofit = new Retrofit.Builder()
                    .baseUrl(GlobalConstant.SERVICE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofitController;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static void setRetrofit(Retrofit retrofit) {
        RetrofitController.retrofit = retrofit;
    }

    public static OkHttpClient.Builder getHttpClient() {
        return httpClient;
    }

    public static void setHttpClient(OkHttpClient.Builder httpClient) {
        RetrofitController.httpClient = httpClient;
    }
}
