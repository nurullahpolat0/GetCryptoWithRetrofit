package com.example.getcryptowithretrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoApi {
    @GET("avgPrice?symbol=BTCUSDT")
    Call<CryptoCurrentAveragePrice> getCrypto();
}
