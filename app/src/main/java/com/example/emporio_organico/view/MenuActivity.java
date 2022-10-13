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
                abrirInfos(v);
                break;
            case 3:
                abrirOndeComprar(v);
                break;
            default:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
    }

    public void abrirInfos(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.maeterra.com.br/sobre-mae-terra/7-principios/organicos-cada-vez-mais.html"));
        startActivity(intent);
    }

    public void abrirOndeComprar(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sintropico.com.br/"));
        startActivity(intent);
    }

}