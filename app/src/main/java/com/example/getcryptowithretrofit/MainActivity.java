package com.example.getcryptowithretrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtCrypto;
    private Button btnGetCrypto;

    private Retrofit retrofit;
    private CryptoApi cryptoApi;
    private String baseURL = "https://api.binance.com/api/v3/";
    private Call<CryptoCurrentAveragePrice> cryptoCurrentAveragePriceCall;
    private CryptoCurrentAveragePrice cryptoCurrentAveragePrice;

    private void init(){
        txtCrypto = findViewById(R.id.textCrypto);
        btnGetCrypto = findViewById(R.id.buttonGetCrypto);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnGetCrypto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRetrofitSettings();
            }
        });
    }

    private void setRetrofitSettings(){

        retrofit = new Retrofit.Builder()
                               .baseUrl(baseURL)
                               .addConverterFactory(GsonConverterFactory.create())
                               .build();
        cryptoApi = retrofit.create(CryptoApi.class);
        cryptoCurrentAveragePriceCall = cryptoApi.getCrypto();

        cryptoCurrentAveragePriceCall.enqueue(new Callback<CryptoCurrentAveragePrice>() {
            @Override
            public void onResponse(Call<CryptoCurrentAveragePrice> call, Response<CryptoCurrentAveragePrice> response) {
                if(response.isSuccessful()){
                    cryptoCurrentAveragePrice = response.body();
                    if(cryptoCurrentAveragePrice != null)
                        btnGetCrypto.setText(String.valueOf(cryptoCurrentAveragePrice.getPrice()));
                }
            }

            @Override
            public void onFailure(Call<CryptoCurrentAveragePrice> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
}