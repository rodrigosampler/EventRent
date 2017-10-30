package br.com.rodrigosampler.eventrent.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.rodrigosampler.eventrent.Entidades.Usuarios;
import br.com.rodrigosampler.eventrent.R;

public class DataActivity extends AppCompatActivity {

    private Button btnAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        btnAvancar = (Button) findViewById(R.id.btnAvancar);

        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaEquipamentos();
            }
        });
    }

    public void abrirTelaEquipamentos(){
        Intent telaEquipamentos = new Intent(DataActivity.this, EquipamentosActivity.class);
        startActivity(telaEquipamentos);
    }
}
