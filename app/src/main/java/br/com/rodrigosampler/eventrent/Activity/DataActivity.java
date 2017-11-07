package br.com.rodrigosampler.eventrent.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.rodrigosampler.eventrent.Entidades.Usuarios;
import br.com.rodrigosampler.eventrent.R;

public class DataActivity extends AppCompatActivity {

    private Button btnAvancar;
    private EditText editData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("");     //Titulo para ser exibido na sua Action Bar em frente à seta

        btnAvancar = (Button) findViewById(R.id.btnAvancar);
        editData = (EditText) findViewById(R.id.editData);

        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editData.getText().toString().equals("")){
                    abrirTelaEquipamentos();
                }else{
                    Toast.makeText(DataActivity.this, "Insira a data do seu evento!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, InicioActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

    public void abrirTelaEquipamentos(){
        Intent telaEquipamentos = new Intent(DataActivity.this, EquipamentosActivity.class);
        startActivity(telaEquipamentos);
    }
}
