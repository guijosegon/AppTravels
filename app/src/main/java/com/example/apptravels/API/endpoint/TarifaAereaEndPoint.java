package com.example.apptravels.API.endpoint;

import com.example.apptravels.banco.model.TarifaAereaModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TarifaAereaEndPoint {
    @GET("tarifaaerea")
    Call<List<TarifaAereaModel>> getTarifasAereas();

    @GET("tarifaaerea/{viagemId}")
    Call<TarifaAereaModel> getTarifaAereaByViagemId(@Path("viagemId") long viagemId);

    @POST("tarifaaerea")
    Call<Void> postTarifaAerea(@Body TarifaAereaModel tarifaAerea);
}
