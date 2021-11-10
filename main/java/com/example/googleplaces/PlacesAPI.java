package com.example.googleplaces;

import com.example.googleplaces.pojos.Places;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlacesAPI {

    @GET("maps/api/place/nearbysearch/json?location=43.6963157243545,-79.49204964597268&radius=1000&types=bakery&key=AIzaSyBtGvgZeAiR4vay_NM7j9LD3vmCHfC3Uvk")
    Call<Places> getPlaces();
}
