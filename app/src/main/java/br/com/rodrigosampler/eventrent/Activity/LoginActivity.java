package br.com.rodrigosampler.eventrent.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.rodrigosampler.eventrent.DAO.ConfiguracaoFirebase;
import br.com.rodrigosampler.eventrent.Entidades.Usuarios;
import br.com.rodrigosampler.eventrent.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private TextView tvAbreCadastro;
    private Button btnLogar;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        tvAbreCadastro = (TextView) findViewById(R.id.tvAbreCadastro);
        btnLogar = (Button) findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editEmail.getText().toString().equals("") && !editSenha.getText().toString().equals("")){
                    usuarios = new Usuarios();
                    usuarios.setEmail(editEmail.getText().toString());
                    usuarios.setSenha(editSenha.getText().toString());

                    validarLogin();
                }else{
                    Toast.makeText(LoginActivity.this, "preencha os campos de e-mail e senha!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvAbreCadastro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                abreCadastroUsuario();
            }
        });
    }

    private void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

            @Override
            public void onComplete(@NonNull Task<AuthResult> task){

                if(task.isSuccessful()){
                    abrirTelaPrincipal();
                    Toast.makeText(LoginActivity.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario ou senha invalidos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirTelaPrincipal(){
        Intent intentAbrirTelaPrincipal = new Intent(LoginActivity.this, InicioActivity.class);
        startActivity(intentAbrirTelaPrincipal);
        finish();
    }

    public void abreCadastroUsuario(){
        Intent intentAbrirCadastro = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intentAbrirCadastro);
    }
}
