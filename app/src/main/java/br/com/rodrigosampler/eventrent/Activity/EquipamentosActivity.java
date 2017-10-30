package br.com.rodrigosampler.eventrent.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.rodrigosampler.eventrent.R;

public class EquipamentosActivity extends AppCompatActivity {

    private ListView listaItems;
    private String[] itens = {
            "Refletor LED", "Moving Wash", "Ribalta LED",
            "Canhão Seguidor"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipamentos);

        listaItems = (ListView) findViewById(R.id.listViewId);

        ArrayAdapter adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );

        listaItems.setAdapter(adapter);


    }
}
