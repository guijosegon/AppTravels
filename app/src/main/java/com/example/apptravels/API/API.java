package com.example.apptravels.API;

import com.example.apptravels.API.endpoint.*;
import com.example.apptravels.banco.model.*;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static final String URL_ROOT = "http://api.genialsaude.com.br/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void getViagens(final Callback<List<ViagemModel>> callback) {
        ViagemEndPoint endPoint = retrofit.create(ViagemEndPoint.class);
        Call<List<ViagemModel>> call = endPoint.getViagens();
        call.enqueue(callback);
    }

    public static void postViagem(ViagemModel viagem, final Callback<Void> callback) {
        ViagemEndPoint endPoint = retrofit.create(ViagemEndPoint.class);
        Call<Void> call = endPoint.postViagem(viagem);
        call.enqueue(callback);
    }

    public static void getViagemById(long id, final Callback<ViagemModel> callback) {
        ViagemEndPoint endPoint = retrofit.create(ViagemEndPoint.class);
        Call<ViagemModel> call = endPoint.getViagemById(id);
        call.enqueue(callback);
    }

    public static void deleteViagem(long id, final Callback<Void> callback) {
        ViagemEndPoint endPoint = retrofit.create(ViagemEndPoint.class);
        Call<Void> call = endPoint.deleteViagem(id);
        call.enqueue(callback);
    }

    public static void getGasolinas(final Callback<List<GasolinaModel>> callback) {
        GasolinaEndPoint endPoint = retrofit.create(GasolinaEndPoint.class);
        Call<List<GasolinaModel>> call = endPoint.getGasolinas();
        call.enqueue(callback);
    }

    public static void postGasolina(GasolinaModel gasolina, final Callback<Void> callback) {
        GasolinaEndPoint endPoint = retrofit.create(GasolinaEndPoint.class);
        Call<Void> call = endPoint.postGasolina(gasolina);
        call.enqueue(callback);
    }

    public static void getGasolinaByViagemId(long viagemId, final Callback<GasolinaModel> callback) {
        GasolinaEndPoint endPoint = retrofit.create(GasolinaEndPoint.class);
        Call<GasolinaModel> call = endPoint.getGasolinaByViagemId(viagemId);
        call.enqueue(callback);
    }

    public static void getTarifasAereas(final Callback<List<TarifaAereaModel>> callback) {
        TarifaAereaEndPoint endPoint = retrofit.create(TarifaAereaEndPoint.class);
        Call<List<TarifaAereaModel>> call = endPoint.getTarifasAereas();
        call.enqueue(callback);
    }

    public static void postTarifaAerea(TarifaAereaModel tarifaAerea, final Callback<Void> callback) {
        TarifaAereaEndPoint endPoint = retrofit.create(TarifaAereaEndPoint.class);
        Call<Void> call = endPoint.postTarifaAerea(tarifaAerea);
        call.enqueue(callback);
    }

    public static void getTarifaAereaByViagemId(long viagemId, final Callback<TarifaAereaModel> callback) {
        TarifaAereaEndPoint endPoint = retrofit.create(TarifaAereaEndPoint.class);
        Call<TarifaAereaModel> call = endPoint.getTarifaAereaByViagemId(viagemId);
        call.enqueue(callback);
    }

    public static void getRefeicoes(final Callback<List<RefeicoesModel>> callback) {
        RefeicoesEndPoint endPoint = retrofit.create(RefeicoesEndPoint.class);
        Call<List<RefeicoesModel>> call = endPoint.getRefeicoes();
        call.enqueue(callback);
    }

    public static void postRefeicoes(RefeicoesModel refeicoes, final Callback<Void> callback) {
        RefeicoesEndPoint endPoint = retrofit.create(RefeicoesEndPoint.class);
        Call<Void> call = endPoint.postRefeicoes(refeicoes);
        call.enqueue(callback);
    }

    public static void getRefeicoesByViagemId(long viagemId, final Callback<RefeicoesModel> callback) {
        RefeicoesEndPoint endPoint = retrofit.create(RefeicoesEndPoint.class);
        Call<RefeicoesModel> call = endPoint.getRefeicoesByViagemId(viagemId);
        call.enqueue(callback);
    }

    public static void getHospedagens(final Callback<List<HospedagemModel>> callback) {
        HospedagemEndPoint endPoint = retrofit.create(HospedagemEndPoint.class);
        Call<List<HospedagemModel>> call = endPoint.getHospedagens();
        call.enqueue(callback);
    }

    public static void postHospedagem(HospedagemModel hospedagem, final Callback<Void> callback) {
        HospedagemEndPoint endPoint = retrofit.create(HospedagemEndPoint.class);
        Call<Void> call = endPoint.postHospedagem(hospedagem);
        call.enqueue(callback);
    }

    public static void getHospedagemByViagemId(long viagemId, final Callback<HospedagemModel> callback) {
        HospedagemEndPoint endPoint = retrofit.create(HospedagemEndPoint.class);
        Call<HospedagemModel> call = endPoint.getHospedagemByViagemId(viagemId);
        call.enqueue(callback);
    }

    public static void getEntretenimentos(final Callback<List<EntretenimentoModel>> callback) {
        EntretenimentoEndPoint endPoint = retrofit.create(EntretenimentoEndPoint.class);
        Call<List<EntretenimentoModel>> call = endPoint.getEntretenimentos();
        call.enqueue(callback);
    }

    public static void postEntretenimento(EntretenimentoModel entretenimento, final Callback<Void> callback) {
        EntretenimentoEndPoint endPoint = retrofit.create(EntretenimentoEndPoint.class);
        Call<Void> call = endPoint.postEntretenimento(entretenimento);
        call.enqueue(callback);
    }

    public static void getEntretenimentoByViagemId(long viagemId, final Callback<List<EntretenimentoModel>> callback) {
        EntretenimentoEndPoint endPoint = retrofit.create(EntretenimentoEndPoint.class);
        Call<List<EntretenimentoModel>> call = endPoint.getEntretenimentoByViagemId(viagemId);
        call.enqueue(callback);
    }

    public static void getUsuarios(final Callback<List<UsuarioModel>> callback) {
        UsuarioEndPoint endPoint = retrofit.create(UsuarioEndPoint.class);
        Call<List<UsuarioModel>> call = endPoint.getUsuarios();
        call.enqueue(callback);
    }

    public static void postUsuario(UsuarioModel usuario, final Callback<Void> callback) {
        UsuarioEndPoint endPoint = retrofit.create(UsuarioEndPoint.class);
        Call<Void> call = endPoint.postUsuario(usuario);
        call.enqueue(callback);
    }

    public static void getUsuarioById(long id, final Callback<UsuarioModel> callback) {
        UsuarioEndPoint endPoint = retrofit.create(UsuarioEndPoint.class);
        Call<UsuarioModel> call = endPoint.getUsuarioById(id);
        call.enqueue(callback);
    }
}
