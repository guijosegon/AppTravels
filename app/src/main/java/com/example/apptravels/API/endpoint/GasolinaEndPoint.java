package com.example.apptravels.API.endpoint;

import com.example.apptravels.banco.model.GasolinaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GasolinaEndPoint {
    @GET("gasolina")
    Call<List<GasolinaModel>> getGasolinas();

    @GET("gasolina/{viagemId}")
    Call<GasolinaModel> getGasolinaByViagemId(@Path("viagemId") long viagemId);

    @POST("gasolina")
    Call<Void> postGasolina(@Body GasolinaModel gasolina);
}
