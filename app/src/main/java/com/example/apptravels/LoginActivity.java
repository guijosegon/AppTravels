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
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin, buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCreateAccount = findViewById(R.id.buttonCreate);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                authenticateUser(username, password);
            }
        });

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ContaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void authenticateUser(final String username, final String password) {
        API.getUsuarios(new Callback<List<UsuarioModel>>() {
            @Override
            public void onResponse(Call<List<UsuarioModel>> call, Response<List<UsuarioModel>> response) {
                if (!response.isSuccessful())
                    Toast.makeText(LoginActivity.this, "Erro ao autenticar usuário", Toast.LENGTH_SHORT).show();

                List<UsuarioModel> usuarios = response.body();
                boolean isAuthenticated = false;

                for (UsuarioModel usuario : usuarios) {
                    if (usuario.getUsuario().equals(username) && usuario.getSenha().equals(password)) {
                        isAuthenticated = true;
                        break;
                    }
                }

                if (isAuthenticated) {
                    Toast.makeText(LoginActivity.this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ViagensActivity.class);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<UsuarioModel>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
