package br.com.rodrigosampler.eventrent.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import br.com.rodrigosampler.eventrent.DAO.ConfiguracaoFirebase;
import br.com.rodrigosampler.eventrent.Entidades.Usuarios;
import br.com.rodrigosampler.eventrent.Helper.Base64Custom;
import br.com.rodrigosampler.eventrent.Helper.Preferencias;
import br.com.rodrigosampler.eventrent.R;

import static android.R.attr.onClick;

public class CadastroActivity extends AppCompatActivity {

    private EditText editCadEmail;
    private EditText editCadNome;
    private EditText editCadSobrenome;
    private EditText editCadSenha;
    private EditText editCadConfirmarSenha;
    private EditText editCadAniversario;
    private RadioButton rbMasculino;
    private RadioButton rbFemenino;
    private Button btnGravar;
    private Usuarios usuarios;
    private FirebaseAuth autenticação;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editCadEmail = (EditText) findViewById(R.id.editCadEmail);
        editCadNome = (EditText) findViewById(R.id.editCadNome);
        editCadSobrenome = (EditText) findViewById(R.id.editCadSobrenome);
        editCadSenha = (EditText) findViewById(R.id.editCadSenha);
        editCadConfirmarSenha = (EditText) findViewById(R.id.editCadConfirmarSenha);
        editCadAniversario = (EditText) findViewById(R.id.editCadAniversario);
        rbFemenino = (RadioButton) findViewById(R.id.rbFemenino);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        btnGravar = (Button) findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editCadSenha.getText().toString().equals(editCadConfirmarSenha.getText().toString())) {
                    usuarios = new Usuarios();
                    usuarios.setNome(editCadNome.getText().toString());
                    usuarios.setEmail(editCadEmail.getText().toString());
                    usuarios.setSenha(editCadSenha.getText().toString());
                    usuarios.setAniversario(editCadAniversario.getText().toString());
                    usuarios.setSobrenome(editCadSobrenome.getText().toString());

                    if (rbFemenino.isChecked()) {
                        usuarios.setSexo("Femenino");
                    } else {
                        usuarios.setSexo("Masculino");
                    }
                cadastrarUsuario();

                } else {
                    Toast.makeText(CadastroActivity.this, "As senhas não são correspondentes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void cadastrarUsuario(){
        autenticação = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticação.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificadorBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(identificadorUsuario);
                    usuarios.salvar();

                    Preferencias preferencias = new Preferencias(CadastroActivity.this);
                    preferencias.salvarUsuarioPreferencias(identificadorUsuario, usuarios.getNome());

                    abrirLoginUsuario();
                }else {
                    String erroExcecao = "";

                    try {
                        throw  task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma senha mais forte, contendo no mínimo 8 caracteres de letras e números";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "O e-mail digitado é invalido, digie um novo e-mail";
                    }catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Esse e-mail ja esta cadastrado no sistema";
                    }catch (Exception e){
                        erroExcecao = "Erro ao efetuar cadastro n o sistema";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, "Erro: " +erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}
