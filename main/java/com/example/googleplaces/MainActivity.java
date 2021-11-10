package com.example.googleplaces;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.googleplaces.pojos.Place;
import com.example.googleplaces.pojos.Places;
import com.google.gson.internal.$Gson$Types;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    Spinner sp1;
    ListView lview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp1=findViewById(R.id.sp1);
        lview1=findViewById(R.id.lview1);
    }

    public void getPlaces(View view) {
        final ProgressDialog ringProgressDialog=ProgressDialog.show(MainActivity.this,"Please Wait","Loading",true);
        ringProgressDialog.setCancelable(true);
        Retrofit r = new Retrofit.Builder().
                baseUrl("https://maps.googleapis.com/").
                addConverterFactory(GsonConverterFactory.create()).build();
        PlacesAPI places = r.create(PlacesAPI.class);
        Call<Places>call = places.getPlaces();
        call.enqueue(new Callback<Places>() {
            @Override
            public void onResponse(Call<Places> call, Response<Places> response) {
                Toast.makeText(MainActivity.this, "Response Successfull", Toast.LENGTH_LONG).show();
                ringProgressDialog.dismiss();
                Places places_ = response.body();
                System.out.println(places_);
                lview1.setAdapter(new MyAdapter(MainActivity.this,places_));
            }

            @Override
            public void onFailure(Call<Places> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No Response", Toast.LENGTH_SHORT).show();
                ringProgressDialog.dismiss();
            }
        });
    }
}