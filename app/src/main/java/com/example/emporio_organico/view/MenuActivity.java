package com.example.emporio_organico.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.emporio_organico.R;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ListView listaOpcoes = this.findViewById(R.id.lista);
        String[] itens = {"Cadastro", "Gerenciamento", "O que é um produto orgânico?", "Onde comprar?"};
        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itens);
        listaOpcoes.setAdapter(arrayItens);
        listaOpcoes.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> listView, View v, int position, long id){
        Intent intent;
        switch(position) {
            case 0:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, ManagerActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, StoreActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, InfosActivity.class);
                startActivity(intent);
                break;
            default:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
    }

}