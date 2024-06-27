package com.example.apptravels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apptravels.API.API;
import com.example.apptravels.banco.model.UsuarioModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContaActivity extends AppCompatActivity {

    private EditText editTextUsernameCreate, editTextPasswordCreate, editTextEmailCreate, editTextPasswordConfirm;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);

        editTextUsernameCreate = findViewById(R.id.editTextUsernameCreate);
        editTextPasswordCreate = findViewById(R.id.editTextPasswordCreate);
        editTextEmailCreate = findViewById(R.id.editTextEmailCreate);
        editTextPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        buttonSalvar = findViewById(R.id.buttonSalvarConta);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsernameCreate.getText().toString();
                String password = editTextPasswordCreate.getText().toString();
                String email = editTextEmailCreate.getText().toString();
                String passwordConfirm = editTextPasswordConfirm.getText().toString();

                if (!checkValidation(username, password, email, passwordConfirm)) {
                    UsuarioModel usuarioView = new UsuarioModel();
                    usuarioView.setUsuario(username);
                    usuarioView.setEmail(email);
                    usuarioView.setSenha(password);

                    API.postUsuario(usuarioView, new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(ContaActivity.this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ContaActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(ContaActivity.this, "Erro ao criar usuário", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(ContaActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(ContaActivity.this, "Campo inválido, favor verificar!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkValidation(String username, String password, String email, String passwordConfirm) {
        return (username.isEmpty() || password.isEmpty() || email.isEmpty() || passwordConfirm.isEmpty() || (!passwordConfirm.equals(password)));
    }
}
