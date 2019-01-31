package com.example.testjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadJson();
    }

    private void loadJson() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service=retrofit.create(ApiService.class);
        Call<List<User>> responseCall=service.getPost();
        responseCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> data = response.body();
                    Log.i("SIZE OF JSON",String.valueOf(data.size()));

                    for (int i=0;i<data.size();i++){
                        Log.i("Data",data.get(i).getBody());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.i("ERROR",t.getLocalizedMessage());
            }
        });
    }

}
