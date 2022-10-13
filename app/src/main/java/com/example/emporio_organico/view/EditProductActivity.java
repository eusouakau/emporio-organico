package com.example.emporio_organico.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.emporio_organico.R;
import com.example.emporio_organico.dao.AppDatabase;
import com.example.emporio_organico.dao.ProductDAO;
import com.example.emporio_organico.entity.Product;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditProductActivity extends AppCompatActivity {

    private TextInputEditText editNome;
    private TextInputEditText editValor;
    private TextInputEditText editDescricao;
    private TextInputEditText editFornecedor;

    private TextInputLayout layoutNome;
    private TextInputLayout layoutValor;
    private TextInputLayout layoutDescricao;
    private TextInputLayout layoutFornecedor;

    AppCompatButton botaoEditar ;
    Product product ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        editNome = this.findViewById(R.id.editTextNome);
        editDescricao =  this.findViewById(R.id.editTextDescricao);
        editValor =  this.findViewById(R.id.editTextValor);
        editFornecedor =  this.findViewById(R.id.editTextFornecedor);

        layoutNome = findViewById(R.id.layoutNome);
        layoutValor = findViewById(R.id.layoutValor);
        layoutDescricao = findViewById((R.id.layoutDescricao));
        layoutFornecedor = findViewById(R.id.layoutFornecedor);

        botaoEditar =  this.findViewById(R.id.saveButton);

        botaoEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alterarProduto(v);
            }
        });
        Bundle extra =  this.getIntent().getExtras();
        product = (Product) getIntent().getSerializableExtra("product");
        editNome.setText(product.getNome());
        editDescricao.setText(product.getDescricao());
        editValor.setText(String.valueOf(product.getValor()));
        editFornecedor.setText(product.getFornecedor());
    }

    private void alterarProduto(View v) {
        Snackbar snackbar;
        if(validarCampos()) {
            product.setNome(editNome.getText().toString().trim());
            product.setDescricao(editDescricao.getText().toString().trim());
            product.setValor(Double.parseDouble(editValor.getText().toString().trim()));
            product.setFornecedor(editFornecedor.getText().toString().trim());

            ProductDAO productDAO = AppDatabase.getInstance(getApplicationContext()).createProductDAO();
            productDAO.update(product);

            snackbar = Snackbar.make(v, "Produto alterado com sucesso!", Snackbar.LENGTH_LONG);
            snackbar.show();

            Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean validarCampos() {
        if(editNome.getText().toString().isEmpty()){
            layoutNome.setErrorEnabled(true);
            layoutNome.setError("O nome é obrigatório!");
            return false;
        } else if(editValor.getText().toString().trim().equals("")) {
            layoutValor.setErrorEnabled(true);
            layoutValor.setError("O valor é obrigatório");
            return false;
        } else if (editDescricao.getText().toString().isEmpty()){
            layoutDescricao.setErrorEnabled(true);
            layoutDescricao.setError("A descricao é obrigatória!");
            return false;
        } else if(editFornecedor.getText().toString().isEmpty()) {
            layoutFornecedor.setErrorEnabled(true);
            layoutFornecedor.setError("O fornecedor é obrigatório!");
            return false;
        } else {
            layoutFornecedor.setErrorEnabled(false);
        }

        return true;
    }

}