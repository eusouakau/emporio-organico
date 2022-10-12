package com.example.emporio_organico.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emporio_organico.R;
import com.example.emporio_organico.dao.AppDatabase;
import com.example.emporio_organico.entity.Product;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText nome;
    private TextInputEditText valor;
    private TextInputEditText descricao;
    private TextInputEditText fornecedor;
    private TextInputLayout layoutNome;
    private TextInputLayout layoutValor;
    private TextInputLayout layoutDescricao;
    private TextInputLayout layoutFornecedor;



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

                layoutNome = findViewById(R.id.layoutNome);
                layoutValor = findViewById(R.id.layoutValor);
                layoutDescricao = findViewById((R.id.layoutDescricao));
                layoutFornecedor = findViewById(R.id.layoutFornecedor);

                Snackbar snackbar;
                if(validarCampos()){
                    Product product = new Product();
                    product.setNome(nome.getText().toString());
                    product.setValor(Double.parseDouble(valor.getText().toString()));
                    product.setDescricao(descricao.getText().toString());
                    product.setFornecedor(fornecedor.getText().toString());
                    AppDatabase.getInstance(getApplicationContext()).createProductDAO().insert(product);
                    snackbar = Snackbar.make(v, "Produto cadastrado com sucesso!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    limparCampos();
                }
                else {
                    snackbar = Snackbar.make(v, "Informações insuficientes para cadastrar produto!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }

    private void limparCampos() {
        layoutNome = findViewById(R.id.layoutNome);
        nome = findViewById(R.id.editTextNome);
        valor = findViewById(R.id.editTextValor);
        descricao = findViewById((R.id.editTextDescricao));
        fornecedor = findViewById((R.id.editTextFornecedor));
        nome.setText("");
        valor.setText("");
        descricao.setText("");
        fornecedor.setText("");
    }

    private boolean validarCampos(){
        if(nome.getText().toString().isEmpty()){
            layoutNome.setErrorEnabled(true);
            layoutNome.setError("O nome é obrigatório!");
            return false;
        } else{
            layoutNome.setErrorEnabled(false);
        }

       if(valor.getText().toString().trim().equals("")){
            layoutValor.setErrorEnabled(true);
            layoutValor.setError("O valor é obrigatório");
            return false;
       } else{
            layoutValor.setErrorEnabled(false);
       }
       if(descricao.getText().toString().isEmpty()){
            layoutDescricao.setErrorEnabled(true);
            layoutDescricao.setError("A descricao é obrigatória!");
            return false;
       } else {
            layoutDescricao.setErrorEnabled(false);
       }

       if(fornecedor.getText().toString().isEmpty()){
            layoutFornecedor.setErrorEnabled(true);
            layoutFornecedor.setError("O fornecedor é obrigatório!");
            return false;
       } else {
            layoutFornecedor.setErrorEnabled(false);
       }

       return true;
    }
}