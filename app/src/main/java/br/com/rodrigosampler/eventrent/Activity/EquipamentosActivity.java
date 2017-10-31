package br.com.rodrigosampler.eventrent.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import br.com.rodrigosampler.eventrent.R;

public class EquipamentosActivity extends AppCompatActivity {

    private ListView listaItems;
    private String[] itens = {
            "Refletor LED", "Moving Wash", "Ribalta LED",
            "Canhão Seguidor"
    };
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipamentos);

        listaItems = (ListView) findViewById(R.id.listViewId);
        button = (Button) findViewById(R.id.proxMapa);

        ArrayAdapter adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );

        listaItems.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirmapa = new Intent(EquipamentosActivity.this, MapsActivity.class);
                startActivity(abrirmapa);
            }
        });


    }
}
