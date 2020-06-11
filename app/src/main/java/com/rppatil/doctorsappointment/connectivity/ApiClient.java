package com.rppatil.doctorsappointment.connectivity;

import com.rppatil.doctorsappointment.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiClient {
    @POST("search_doctor_on_clinicid")
    @FormUrlEncoded
    Call<ServerResponse> getdoctorsdata(@Field("clinic_id") int clinic_id);
}
