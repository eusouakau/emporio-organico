package com.example.emporio_organico.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emporio_organico.R;
import com.example.emporio_organico.dao.AppDatabase;
import com.example.emporio_organico.dao.ProductDAO;
import com.example.emporio_organico.entity.Product;

public class EditProductActivity extends AppCompatActivity {

    EditText editTextNome ;
    EditText editTextDesc;
    EditText editTextValor;
    EditText editTextFornecedor;
    AppCompatButton botaoEditar ;
    Product product ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        editTextNome = this.findViewById(R.id.editTextNomeEdit);
        editTextDesc =  this.findViewById(R.id.editTextDescricaoEdit);
        editTextValor =  this.findViewById(R.id.editTextValorEdit);
        editTextFornecedor =  this.findViewById(R.id.editTextFornecedor);
        botaoEditar =  this.findViewById(R.id.saveButton);

        botaoEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alterarProduto();
            }
        });
        Bundle extra =  this.getIntent().getExtras();
        product = (Product) getIntent().getSerializableExtra("product");
        editTextNome.setText(product.getNome());
        editTextDesc.setText(product.getDescricao());
        editTextValor.setText(String.valueOf(product.getValor()));
        editTextFornecedor.setText(product.getFornecedor());
    }

    private void alterarProduto() {
        if (editTextNome.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Nome é obrigatório!", Toast.LENGTH_LONG).show();
            editTextNome.requestFocus();
        } else if (editTextDesc.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Descrição é obrigatório!", Toast.LENGTH_LONG).show();
            editTextDesc.requestFocus();
        } else if (editTextValor.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "O valor é obrigatório!", Toast.LENGTH_LONG).show();
            editTextValor.requestFocus();
        } else if (editTextFornecedor.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "O fornecedor é obrigatório!", Toast.LENGTH_LONG).show();
            editTextFornecedor.requestFocus();
        } else {
            product.setNome(editTextNome.getText().toString().trim());
            product.setDescricao(editTextDesc.getText().toString().trim());
            product.setValor(Double.parseDouble(editTextValor.getText().toString().trim()));
            product.setFornecedor(editTextFornecedor.getText().toString().trim());
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

    public void cancelarEdicao(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}