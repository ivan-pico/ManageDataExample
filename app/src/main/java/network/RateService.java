package network;

import entity.OpenRate;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ipicomar on 12/03/2018.
 */

public interface RateService {

    // app_id=fb478696e5d1403ab4c356be6e4dea8f&base=USD
    // base=USD

    @GET("/latest.json")
    public Call<OpenRate> getUSDRates(
            @Query("app_id") String app_id,
            @Query("base") String base);
}
