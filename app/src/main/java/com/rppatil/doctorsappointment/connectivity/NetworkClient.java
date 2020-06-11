package com.rppatil.doctorsappointment.connectivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    private static final String BASE_URL="http://174.141.230.138/~rxondemand/webservices_1_0/";
    private static NetworkClient netweorkClient;
    private Retrofit retrofit;
    private NetworkClient(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized NetworkClient getInstance(){
        if (netweorkClient==null){
            netweorkClient=new NetworkClient();
        }
        return netweorkClient;
    }
    public ApiClient getApiClient(){
        return retrofit.create(ApiClient.class);
    }
}
