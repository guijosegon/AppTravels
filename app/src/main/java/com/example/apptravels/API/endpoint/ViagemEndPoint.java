package com.example.apptravels.API.endpoint;

import com.example.apptravels.banco.model.ViagemModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface ViagemEndPoint {
    @GET("viagem")
    Call<List<ViagemModel>> getViagens();

    @GET("viagem/{id}")
    Call<ViagemModel> getViagemById(@Path("id") long id);

    @POST("viagem")
    Call<Void> postViagem(@Body ViagemModel viagem);

    @DELETE("viagem/{id}")
    Call<Void> deleteViagem(@Path("id") long id);
}
