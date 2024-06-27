package com.example.apptravels.API.endpoint;

import com.example.apptravels.banco.model.RefeicoesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RefeicoesEndPoint {
    @GET("refeicoes")
    Call<List<RefeicoesModel>> getRefeicoes();

    @GET("refeicoes/{viagemId}")
    Call<RefeicoesModel> getRefeicoesByViagemId(@Path("viagemId") long viagemId);

    @POST("refeicoes")
    Call<Void> postRefeicoes(@Body RefeicoesModel refeicoes);
}
