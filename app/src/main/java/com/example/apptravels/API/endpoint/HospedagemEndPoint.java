package com.example.apptravels.API.endpoint;

import com.example.apptravels.banco.model.HospedagemModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HospedagemEndPoint {
    @GET("hospedagem")
    Call<List<HospedagemModel>> getHospedagens();

    @GET("hospedagem/{viagemId}")
    Call<HospedagemModel> getHospedagemByViagemId(@Path("viagemId") long viagemId);

    @POST("hospedagem")
    Call<Void> postHospedagem(@Body HospedagemModel hospedagem);
}
