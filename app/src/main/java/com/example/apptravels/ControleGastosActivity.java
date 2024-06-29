package com.example.apptravels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apptravels.API.API;
import com.example.apptravels.banco.model.EntretenimentoModel;
import com.example.apptravels.banco.model.GasolinaModel;
import com.example.apptravels.banco.model.HospedagemModel;
import com.example.apptravels.banco.model.RefeicoesModel;
import com.example.apptravels.banco.model.TarifaAereaModel;
import com.example.apptravels.banco.model.ViagemModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControleGastosActivity extends AppCompatActivity {

    private EditText editTextTotalKm, editTextKmPorLitro, editTextCustoPorLitro, editTextTotalVeiculos;
    private CheckBox checkBoxAddGasolina;
    private EditText editTextCustoPorPessoa, editTextAluguelVeiculo;
    private CheckBox checkBoxAddTarifa;
    private EditText editTextCustoPorRefeicao, editTextRefeicoesPorDia;
    private CheckBox checkBoxAddRefeicoes;
    private EditText editTextCustoPorNoite, editTextTotalNoites, editTextTotalQuartos;
    private CheckBox checkBoxAddHospedagem;
    private EditText editTextDescricaoEntretenimento, editTextCustoEntretenimento;
    private CheckBox checkBoxAddEntretenimento;
    private Button buttonSalvarGastos;
    private Button buttonDeleteViagem;
    private TextView textViewRelatorio;
    private long viagemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_gastos);

        viagemId = getIntent().getLongExtra("VIAGEM_ID", -1);

        if (viagemId == -1) {
            finish();
            return;
        }

        editTextTotalKm = findViewById(R.id.editTextTotalKm);
        editTextKmPorLitro = findViewById(R.id.editTextKmPorLitro);
        editTextCustoPorLitro = findViewById(R.id.editTextCustoPorLitro);
        editTextTotalVeiculos = findViewById(R.id.editTextTotalVeiculos);
        checkBoxAddGasolina = findViewById(R.id.checkBoxAddGasolina);

        editTextCustoPorPessoa = findViewById(R.id.editTextCustoPorPessoa);
        editTextAluguelVeiculo = findViewById(R.id.editTextAluguelVeiculo);
        checkBoxAddTarifa = findViewById(R.id.checkBoxAddTarifa);

        editTextCustoPorRefeicao = findViewById(R.id.editTextCustoPorRefeicao);
        editTextRefeicoesPorDia = findViewById(R.id.editTextRefeicoesPorDia);
        checkBoxAddRefeicoes = findViewById(R.id.checkBoxAddRefeicoes);

        editTextCustoPorNoite = findViewById(R.id.editTextCustoPorNoite);
        editTextTotalNoites = findViewById(R.id.editTextTotalNoites);
        editTextTotalQuartos = findViewById(R.id.editTextTotalQuartos);
        checkBoxAddHospedagem = findViewById(R.id.checkBoxAddHospedagem);

        editTextDescricaoEntretenimento = findViewById(R.id.editTextDescricaoEntretenimento);
        editTextCustoEntretenimento = findViewById(R.id.editTextCustoEntretenimento);
        checkBoxAddEntretenimento = findViewById(R.id.checkBoxAddEntretenimento);

        buttonSalvarGastos = findViewById(R.id.buttonSalvarGastos);
        buttonDeleteViagem = findViewById(R.id.buttonDeleteViagem);
        textViewRelatorio = findViewById(R.id.textViewRelatorio);

        buttonDeleteViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarViagem();
            }
        });

        buttonSalvarGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarGastos();
            }
        });

        carregarGastos();
    }

    private void deletarViagem() {
        API.deleteViagem(viagemId, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ControleGastosActivity.this, "Viagem deletada com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ControleGastosActivity.this, ViagensActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(ControleGastosActivity.this, "Erro ao deletar viagem", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ControleGastosActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void salvarGastos() {
        try {
            double totalKm = Double.parseDouble(editTextTotalKm.getText().toString());
            double kmPorLitro = Double.parseDouble(editTextKmPorLitro.getText().toString());
            double custoPorLitro = Double.parseDouble(editTextCustoPorLitro.getText().toString());
            int totalVeiculos = Integer.parseInt(editTextTotalVeiculos.getText().toString());
            boolean addGasolina = checkBoxAddGasolina.isChecked();
            double custoTotalGasolina = (totalKm / kmPorLitro) * custoPorLitro / totalVeiculos;

            GasolinaModel gasolinaModel = new GasolinaModel();
            gasolinaModel.setViagemId(viagemId);
            gasolinaModel.setTotalKm(totalKm);
            gasolinaModel.setKmPorLitro(kmPorLitro);
            gasolinaModel.setCustoPorLitro(custoPorLitro);
            gasolinaModel.setTotalVeiculos(totalVeiculos);
            gasolinaModel.setCustoTotalGasolina(custoTotalGasolina);
            gasolinaModel.setAddGasolina(addGasolina);

            API.postGasolina(gasolinaModel, new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful())
                        Toast.makeText(ControleGastosActivity.this, "Erro ao salvar gastos de gasolina", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ControleGastosActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            double custoPorPessoa = Double.parseDouble(editTextCustoPorPessoa.getText().toString());
            double aluguelVeiculo = Double.parseDouble(editTextAluguelVeiculo.getText().toString());
            boolean addTarifa = checkBoxAddTarifa.isChecked();
            double custoTotalTarifa = (custoPorPessoa * totalVeiculos) + aluguelVeiculo;

            TarifaAereaModel tarifaAereaModel = new TarifaAereaModel();
            tarifaAereaModel.setViagemId(viagemId);
            tarifaAereaModel.setCustoPorPessoa(custoPorPessoa);
            tarifaAereaModel.setAluguelVeiculo(aluguelVeiculo);
            tarifaAereaModel.setCustoTotalTarifa(custoTotalTarifa);
            tarifaAereaModel.setAddTarifa(addTarifa);

            API.postTarifaAerea(tarifaAereaModel, new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful())
                        Toast.makeText(ControleGastosActivity.this, "Erro ao salvar gastos de tarifa aérea", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ControleGastosActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            double custoPorRefeicao = Double.parseDouble(editTextCustoPorRefeicao.getText().toString());
            int refeicoesPorDia = Integer.parseInt(editTextRefeicoesPorDia.getText().toString());
            boolean addRefeicoes = checkBoxAddRefeicoes.isChecked();
            int duracaoViagem = getDuracaoViagem();
            double custoTotalRefeicoes = (refeicoesPorDia * custoPorRefeicao * totalVeiculos) * duracaoViagem;

            RefeicoesModel refeicoesModel = new RefeicoesModel();
            refeicoesModel.setViagemId(viagemId);
            refeicoesModel.setCustoPorRefeicao(custoPorRefeicao);
            refeicoesModel.setRefeicoesPorDia(refeicoesPorDia);
            refeicoesModel.setCustoTotalRefeicoes(custoTotalRefeicoes);
            refeicoesModel.setAddRefeicoes(addRefeicoes);

            API.postRefeicoes(refeicoesModel, new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful())
                        Toast.makeText(ControleGastosActivity.this, "Erro ao salvar gastos de refeições", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ControleGastosActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            double custoPorNoite = Double.parseDouble(editTextCustoPorNoite.getText().toString());
            int totalNoites = Integer.parseInt(editTextTotalNoites.getText().toString());
            int totalQuartos = Integer.parseInt(editTextTotalQuartos.getText().toString());
            boolean addHospedagem = checkBoxAddHospedagem.isChecked();
            double custoTotalHospedagem = custoPorNoite * totalNoites * totalQuartos;

            HospedagemModel hospedagemModel = new HospedagemModel();
            hospedagemModel.setViagemId(viagemId);
            hospedagemModel.setCustoPorNoite(custoPorNoite);
            hospedagemModel.setTotalNoites(totalNoites);
            hospedagemModel.setTotalQuartos(totalQuartos);
            hospedagemModel.setCustoTotalHospedagem(custoTotalHospedagem);
            hospedagemModel.setAddHospedagem(addHospedagem);

            API.postHospedagem(hospedagemModel, new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful())
                        Toast.makeText(ControleGastosActivity.this, "Erro ao salvar gastos de hospedagem", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ControleGastosActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            String descricaoEntretenimento = editTextDescricaoEntretenimento.getText().toString();
            double custoEntretenimento = Double.parseDouble(editTextCustoEntretenimento.getText().toString());
            boolean addEntretenimento = checkBoxAddEntretenimento.isChecked();

            EntretenimentoModel entretenimentoModel = new EntretenimentoModel();
            entretenimentoModel.setViagemId(viagemId);
            entretenimentoModel.setDescricao(descricaoEntretenimento);
            entretenimentoModel.setCusto(custoEntretenimento);
            entretenimentoModel.setAddEntretenimento(addEntretenimento);

            API.postEntretenimento(entretenimentoModel, new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful())
                        Toast.makeText(ControleGastosActivity.this, "Erro ao salvar gastos de entretenimento", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ControleGastosActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            textViewRelatorio.setText(gerarRelatorio());
            Toast.makeText(this, "Gastos salvos com sucesso!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Erro ao salvar gastos: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void carregarGastos() {
        API.getGasolinaByViagemId(viagemId, new Callback<GasolinaModel>() {
            @Override
            public void onResponse(Call<GasolinaModel> call, Response<GasolinaModel> response) {
                if (!response.isSuccessful()) return;

                GasolinaModel gasolina = response.body();
                if (gasolina == null) return;

                editTextTotalKm.setText(String.valueOf(gasolina.getTotalKm()));
                editTextKmPorLitro.setText(String.valueOf(gasolina.getKmPorLitro()));
                editTextCustoPorLitro.setText(String.valueOf(gasolina.getCustoPorLitro()));
                editTextTotalVeiculos.setText(String.valueOf(gasolina.getTotalVeiculos()));
                checkBoxAddGasolina.setChecked(gasolina.isAddGasolina());
            }

            @Override
            public void onFailure(Call<GasolinaModel> call, Throwable t) {
                Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de gasolina: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        API.getTarifaAereaByViagemId(viagemId, new Callback<TarifaAereaModel>() {
            @Override
            public void onResponse(Call<TarifaAereaModel> call, Response<TarifaAereaModel> response) {
                if (!response.isSuccessful()) return;

                TarifaAereaModel tarifaAerea = response.body();
                if (tarifaAerea == null) return;

                editTextCustoPorPessoa.setText(String.valueOf(tarifaAerea.getCustoPorPessoa()));
                editTextAluguelVeiculo.setText(String.valueOf(tarifaAerea.getAluguelVeiculo()));
                checkBoxAddTarifa.setChecked(tarifaAerea.isAddTarifa());
            }

            @Override
            public void onFailure(Call<TarifaAereaModel> call, Throwable t) {
                Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de tarifa aérea: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        API.getRefeicoesByViagemId(viagemId, new Callback<RefeicoesModel>() {
            @Override
            public void onResponse(Call<RefeicoesModel> call, Response<RefeicoesModel> response) {
                if (!response.isSuccessful()) return;

                RefeicoesModel refeicoes = response.body();
                if (refeicoes == null) return;

                editTextCustoPorRefeicao.setText(String.valueOf(refeicoes.getCustoPorRefeicao()));
                editTextRefeicoesPorDia.setText(String.valueOf(refeicoes.getRefeicoesPorDia()));
                checkBoxAddRefeicoes.setChecked(refeicoes.isAddRefeicoes());
            }

            @Override
            public void onFailure(Call<RefeicoesModel> call, Throwable t) {
                Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de refeições: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        API.getHospedagemByViagemId(viagemId, new Callback<HospedagemModel>() {
            @Override
            public void onResponse(Call<HospedagemModel> call, Response<HospedagemModel> response) {
                if (!response.isSuccessful()) return;

                HospedagemModel hospedagem = response.body();
                if (hospedagem == null) return;

                editTextCustoPorNoite.setText(String.valueOf(hospedagem.getCustoPorNoite()));
                editTextTotalNoites.setText(String.valueOf(hospedagem.getTotalNoites()));
                editTextTotalQuartos.setText(String.valueOf(hospedagem.getTotalQuartos()));
                checkBoxAddHospedagem.setChecked(hospedagem.isAddHospedagem());
            }

            @Override
            public void onFailure(Call<HospedagemModel> call, Throwable t) {
                Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de hospedagem: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        API.getEntretenimentoByViagemId(viagemId, new Callback<List<EntretenimentoModel>>() {
            @Override
            public void onResponse(Call<List<EntretenimentoModel>> call, Response<List<EntretenimentoModel>> response) {
                if (!response.isSuccessful()) return;

                List<EntretenimentoModel> entretenimentoList = response.body();
                if (entretenimentoList == null || entretenimentoList.isEmpty()) return;

                EntretenimentoModel entretenimento = entretenimentoList.get(0);
                editTextDescricaoEntretenimento.setText(entretenimento.getDescricao());
                editTextCustoEntretenimento.setText(String.valueOf(entretenimento.getCusto()));
                checkBoxAddEntretenimento.setChecked(entretenimento.isAddEntretenimento());
            }

            @Override
            public void onFailure(Call<List<EntretenimentoModel>> call, Throwable t) {
                Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de entretenimento: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        textViewRelatorio.setText(gerarRelatorio());
    }

    private int getDuracaoViagem() {
        final int[] duracaoViagem = {0};
        API.getHospedagemByViagemId(viagemId, new Callback<HospedagemModel>() {
            @Override
            public void onResponse(Call<HospedagemModel> call, Response<HospedagemModel> response) {
                if (!response.isSuccessful()) return;

                HospedagemModel hospedagem = response.body();
                if (hospedagem == null)  return;

                duracaoViagem[0] = hospedagem.getTotalNoites();
            }

            @Override
            public void onFailure(Call<HospedagemModel> call, Throwable t) {
                Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de hospedagem: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return duracaoViagem[0];
    }

    private String gerarRelatorio() {
        StringBuilder report = new StringBuilder();
        double[] totalCost = {0};

        API.getViagemById(viagemId, new Callback<ViagemModel>() {
            @Override
            public void onResponse(Call<ViagemModel> call, Response<ViagemModel> response) {
                if (!response.isSuccessful()) return;

                ViagemModel viagem = response.body();
                if (viagem == null) return;

                int numPessoas = viagem.getNumPessoas();
                String destino = viagem.getDestino();
                String dataViagem = viagem.getDataViagem();

                API.getGasolinaByViagemId(viagemId, new Callback<GasolinaModel>() {
                    @Override
                    public void onResponse(Call<GasolinaModel> call, Response<GasolinaModel> response) {
                        if (!response.isSuccessful()) return;

                        GasolinaModel gasolina = response.body();
                        if (gasolina == null) return;

                        double custoTotalGasolina = ((gasolina.getTotalKm() / gasolina.getKmPorLitro()) * gasolina.getCustoPorLitro()) / gasolina.getTotalVeiculos();
                        report.append("**Gasolina**\n");
                        report.append("Custo Total: R$ ").append(String.format("%.2f", custoTotalGasolina)).append("\n\n");
                        totalCost[0] += custoTotalGasolina;
                        atualizarRelatorio(report, totalCost[0], numPessoas, destino, dataViagem);
                    }

                    @Override
                    public void onFailure(Call<GasolinaModel> call, Throwable t) {
                        Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de gasolina: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                API.getTarifaAereaByViagemId(viagemId, new Callback<TarifaAereaModel>() {
                    @Override
                    public void onResponse(Call<TarifaAereaModel> call, Response<TarifaAereaModel> response) {
                        if (!response.isSuccessful()) return;

                        TarifaAereaModel tarifaAerea = response.body();
                        if (tarifaAerea == null) return;

                        double custoTotalTarifa = (tarifaAerea.getCustoPorPessoa() * numPessoas) + tarifaAerea.getAluguelVeiculo();
                        report.append("**Tarifa Aérea**\n");
                        report.append("Custo Total: R$ ").append(String.format("%.2f", custoTotalTarifa)).append("\n\n");
                        totalCost[0] += custoTotalTarifa;
                        atualizarRelatorio(report, totalCost[0], numPessoas, destino, dataViagem);
                    }

                    @Override
                    public void onFailure(Call<TarifaAereaModel> call, Throwable t) {
                        Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de tarifa aérea: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                API.getRefeicoesByViagemId(viagemId, new Callback<RefeicoesModel>() {
                    @Override
                    public void onResponse(Call<RefeicoesModel> call, Response<RefeicoesModel> response) {
                        if (!response.isSuccessful()) return;

                        RefeicoesModel refeicoes = response.body();
                        if (refeicoes == null) return;

                        int duracaoViagem = getDuracaoViagem();
                        double custoTotalRefeicoes = (refeicoes.getRefeicoesPorDia() * numPessoas * refeicoes.getCustoPorRefeicao()) * duracaoViagem;
                        report.append("**Refeições**\n");
                        report.append("Custo Total: R$ ").append(String.format("%.2f", custoTotalRefeicoes)).append("\n\n");
                        totalCost[0] += custoTotalRefeicoes;
                        atualizarRelatorio(report, totalCost[0], numPessoas, destino, dataViagem);
                    }

                    @Override
                    public void onFailure(Call<RefeicoesModel> call, Throwable t) {
                        Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de refeições: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                API.getHospedagemByViagemId(viagemId, new Callback<HospedagemModel>() {
                    @Override
                    public void onResponse(Call<HospedagemModel> call, Response<HospedagemModel> response) {
                        if (!response.isSuccessful()) return;

                        HospedagemModel hospedagem = response.body();
                        if (hospedagem == null) return;

                        double custoTotalHospedagem = (hospedagem.getCustoPorNoite() * hospedagem.getTotalNoites()) * hospedagem.getTotalQuartos();
                        report.append("**Hospedagem**\n");
                        report.append("Custo Total: R$ ").append(String.format("%.2f", custoTotalHospedagem)).append("\n\n");
                        totalCost[0] += custoTotalHospedagem;
                        atualizarRelatorio(report, totalCost[0], numPessoas, destino, dataViagem);
                    }

                    @Override
                    public void onFailure(Call<HospedagemModel> call, Throwable t) {
                        Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de hospedagem: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                API.getEntretenimentoByViagemId(viagemId, new Callback<List<EntretenimentoModel>>() {
                    @Override
                    public void onResponse(Call<List<EntretenimentoModel>> call, Response<List<EntretenimentoModel>> response) {
                        if (!response.isSuccessful()) return;

                        List<EntretenimentoModel> entretenimentoList = response.body();
                        double custoTotalEntretenimento = 0;
                        if (entretenimentoList == null || entretenimentoList.isEmpty()) return;

                        report.append("**Entretenimento/Diversos**\n");
                        for (EntretenimentoModel entretenimento : entretenimentoList) {
                            if (entretenimento.isAddEntretenimento()) {
                                custoTotalEntretenimento += entretenimento.getCusto();
                            }
                        }
                        report.append("Custo Total: R$ ").append(String.format("%.2f", custoTotalEntretenimento)).append("\n\n");
                        totalCost[0] += custoTotalEntretenimento;
                        atualizarRelatorio(report, totalCost[0], numPessoas, destino, dataViagem);
                    }

                    @Override
                    public void onFailure(Call<List<EntretenimentoModel>> call, Throwable t) {
                        Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados de entretenimento: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<ViagemModel> call, Throwable t) {
                Toast.makeText(ControleGastosActivity.this, "Erro ao carregar dados da viagem: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return report.toString();
    }

    private void atualizarRelatorio(StringBuilder report, double totalCost, int numPessoas, String destino, String dataViagem) {
        double custoTotalPorPessoa = totalCost / numPessoas;
        int duracaoViagem = getDuracaoViagem();

        report.append("**Resumo da Viagem**\n");
        report.append("Destino: ").append(destino).append("\n");
        report.append("Data da Viagem: ").append(dataViagem).append("\n");
        report.append("Número de Pessoas: ").append(numPessoas).append("\n");
        report.append("Duração da Viagem: ").append(duracaoViagem).append(" dias\n");
        report.append("Custo Total: R$ ").append(String.format("%.2f", totalCost)).append("\n");
        report.append("Custo Total por Pessoa: R$ ").append(String.format("%.2f", custoTotalPorPessoa)).append("\n");

        textViewRelatorio.setText(report.toString());
    }

}
