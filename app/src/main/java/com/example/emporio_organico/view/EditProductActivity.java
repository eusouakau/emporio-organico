package com.example.emporio_organico.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.emporio_organico.R;
import com.example.emporio_organico.dao.AppDatabase;
import com.example.emporio_organico.dao.ProductDAO;
import com.example.emporio_organico.entity.Product;
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
        botaoEditar =  this.findViewById(R.id.saveButton);

        botaoEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alterarProduto();
            }
        });
        Bundle extra =  this.getIntent().getExtras();
        product = (Product) getIntent().getSerializableExtra("product");
        editNome.setText(product.getNome());
        editDescricao.setText(product.getDescricao());
        editValor.setText(String.valueOf(product.getValor()));
        editFornecedor.setText(product.getFornecedor());
    }

    private void alterarProduto() {
        if (editNome.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Nome é obrigatório!", Toast.LENGTH_LONG).show();
            editNome.requestFocus();
        } else if (editDescricao.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Descrição é obrigatório!", Toast.LENGTH_LONG).show();
            editDescricao.requestFocus();
        } else if (editValor.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "O valor é obrigatório!", Toast.LENGTH_LONG).show();
            editValor.requestFocus();
        } else if (editFornecedor.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "O fornecedor é obrigatório!", Toast.LENGTH_LONG).show();
            editFornecedor.requestFocus();
        } else {
            product.setNome(editNome.getText().toString().trim());
            product.setDescricao(editDescricao.getText().toString().trim());
            product.setValor(Double.parseDouble(editValor.getText().toString().trim()));
            product.setFornecedor(editFornecedor.getText().toString().trim());
            product.setNome(editNome.getText().toString().trim());
            product.setDescricao(editDescricao.getText().toString().trim());
            product.setValor(Double.parseDouble(editValor.getText().toString().trim()));
            product.setFornecedor(editFornecedor.getText().toString().trim());
            ProductDAO productDAO = AppDatabase.getInstance(getApplicationContext()).createProductDAO();
            productDAO.update(product);
            mostraMensagem();
        }
    }

    public void mostraMensagem(){
        String msg = "Produto alterado com sucesso! ";
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.app_name);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alertDialog.show();
    }
}