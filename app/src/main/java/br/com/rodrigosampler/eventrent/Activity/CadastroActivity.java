package br.com.rodrigosampler.eventrent.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import br.com.rodrigosampler.eventrent.DAO.ConfiguracaoFirebase;
import br.com.rodrigosampler.eventrent.Entidades.Usuarios;
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
    private FirebaseAuth autenticação

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


                } else {
                    Toast.makeText(CadastroActivity.this, "As senhas não são correspondentes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void cadastroUsuario(){
        autenticação = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticação.createUserWithEmailAndPassword(
                usuarios
        );
    }
}
