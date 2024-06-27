package com.example.apptravels;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apptravels.banco.dao.ViagemDAO;
import com.example.apptravels.banco.model.ViagemModel;
import com.example.apptravels.API.API;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViagensActivity extends AppCompatActivity {

    private EditText editTextDestino, editTextDataViagem, editTextNumPessoas;
    private Button buttonAddViagem;
    private ListView listViewViagens;
    private ViagemDAO viagemDAO;
    private ViagemAdapter adapter;
    private List<ViagemModel> listaViagens;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens);

        editTextDestino = findViewById(R.id.editTextDestino);
        editTextDataViagem = findViewById(R.id.editTextDataViagem);
        editTextNumPessoas = findViewById(R.id.editTextNumPessoas);
        buttonAddViagem = findViewById(R.id.buttonAddViagem);
        listViewViagens = findViewById(R.id.listViewViagens);

        viagemDAO = new ViagemDAO(this);
        viagemDAO.OpenBanco();

        listaViagens = new ArrayList<>();
        adapter = new ViagemAdapter(this, listaViagens);
        listViewViagens.setAdapter(adapter);

        calendar = Calendar.getInstance();

        editTextDataViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ViagensActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                editTextDataViagem.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        buttonAddViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addViagem();
            }
        });

        listViewViagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViagemModel viagem = listaViagens.get(position);
                Intent intent = new Intent(ViagensActivity.this, ControleGastosActivity.class);
                intent.putExtra("VIAGEM_ID", viagem.getId());
                startActivity(intent);
            }
        });

        loadViagens();
    }

    protected void onResume() {
        super.onResume();
        loadViagens();
    }

    private void addViagem() {
        String destino = editTextDestino.getText().toString();
        String dataViagem = editTextDataViagem.getText().toString();
        int numPessoas;

        try {
            numPessoas = Integer.parseInt(editTextNumPessoas.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show();
            return;
        }

        ViagemModel viagem = new ViagemModel();
        viagem.setDestino(destino);
        viagem.setDataViagem(dataViagem);
        viagem.setNumPessoas(numPessoas);

        API.postViagem(viagem, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ViagensActivity.this, "Viagem adicionada com sucesso!", Toast.LENGTH_SHORT).show();
                    loadViagens();

                    editTextDestino.setText("");
                    editTextDataViagem.setText("");
                    editTextNumPessoas.setText("");
                } else {
                    Toast.makeText(ViagensActivity.this, "Erro ao adicionar viagem", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ViagensActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadViagens() {
        API.getViagens(new Callback<List<ViagemModel>>() {
            @Override
            public void onResponse(Call<List<ViagemModel>> call, Response<List<ViagemModel>> response) {
                if (response.isSuccessful()) {
                    listaViagens.clear();
                    listaViagens.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ViagensActivity.this, "Erro ao carregar viagens", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ViagemModel>> call, Throwable t) {
                Toast.makeText(ViagensActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viagemDAO.CloseBanco();
    }
}
