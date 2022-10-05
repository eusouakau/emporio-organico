package com.example.emporio_organico.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emporio_organico.R;
import com.example.emporio_organico.dao.AppDatabase;
import com.example.emporio_organico.entity.Product;

public class RegisterActivity extends AppCompatActivity {

    EditText nome;
    EditText valor;
    EditText descricao;
    EditText fornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button cadastrar= findViewById(R.id.registerButton);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {
                nome = findViewById(R.id.editTextNome);
                valor = findViewById(R.id.editTextValor);
                descricao = findViewById((R.id.editTextDescricao));
                fornecedor = findViewById(R.id.editTextFornecedor);
                Product product = new Product();
                product.setNome(nome.getText().toString());
                product.setValor(Double.parseDouble(valor.getText().toString()));
                product.setDescricao(descricao.getText().toString());
                product.setFornecedor(fornecedor.getText().toString());
                AppDatabase.getInstance(getApplicationContext()).createProductDAO().insert(product);
                Toast.makeText(getApplicationContext(), "Novo produto cadastrado!", Toast.LENGTH_LONG).show();
                limparCampos();
            }
        });
    }

    public void cancelarCadastro(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void limparCampos() {
        nome = findViewById(R.id.editTextNome);
        valor = findViewById(R.id.editTextValor);
        descricao = findViewById((R.id.editTextDescricao));
        fornecedor = findViewById((R.id.editTextFornecedor));
        nome.setText("");
        valor.setText("");
        descricao.setText("");
        fornecedor.setText("");
    }

}