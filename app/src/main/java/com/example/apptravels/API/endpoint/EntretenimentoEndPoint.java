package com.example.apptravels.API.endpoint;

import com.example.apptravels.banco.model.EntretenimentoModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EntretenimentoEndPoint {
    @GET("entretenimento")
    Call<List<EntretenimentoModel>> getEntretenimentos();

    @GET("entretenimento/{viagemId}")
    Call<List<EntretenimentoModel>> getEntretenimentoByViagemId(@Path("viagemId") long viagemId);

    @POST("entretenimento")
    Call<Void> postEntretenimento(@Body EntretenimentoModel entretenimento);
}
