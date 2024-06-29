package com.example.apptravels.API.endpoint;

import com.example.apptravels.banco.model.UsuarioModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioEndPoint {
    @GET("usuario")
    Call<List<UsuarioModel>> getUsuarios();

    @GET("usuario/{id}")
    Call<UsuarioModel> getUsuarioById(@Path("id") long id);

    @POST("usuario")
    Call<Void> postUsuario(@Body UsuarioModel usuario);
}
