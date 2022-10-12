package com.example.emporio_organico.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.emporio_organico.R;
import com.example.emporio_organico.dao.AppDatabase;
import com.example.emporio_organico.dao.ProductDAO;
import com.example.emporio_organico.entity.Product;
import com.example.emporio_organico.view.EditProductActivity;
import com.example.emporio_organico.view.ManagerActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class LinhaGerenciamentoAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater = null;
    List<Product> products;
    private ManagerActivity listarProdutos;

    public LinhaGerenciamentoAdapter(ManagerActivity listarProdutos, List<Product> products ) {
        this.products = products;
        this.listarProdutos =  listarProdutos;
        this.layoutInflater = (LayoutInflater) this.listarProdutos.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_manager_row,null);

        AppCompatTextView textViewNome  =  viewLinhaLista.findViewById(R.id.textViewNome);
        AppCompatTextView textViewValor = viewLinhaLista.findViewById(R.id.textViewValor);
        AppCompatTextView textViewDescricao = viewLinhaLista.findViewById(R.id.textViewDescricao);
        AppCompatTextView textViewFornecedor = viewLinhaLista.findViewById(R.id.textViewFornecedor);
        ImageButton buttonExcluir = viewLinhaLista.findViewById(R.id.buttonExcluir);
        ImageButton buttonEditar = viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewNome.setText(products.get(position).getNome());
        textViewValor.setText(String.valueOf(products.get(position).getValor()));
        textViewDescricao.setText(products.get(position).getDescricao());
        textViewFornecedor.setText(products.get(position).getFornecedor());

        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDAO productDAO = AppDatabase.getInstance(listarProdutos.getApplicationContext()).createProductDAO();
                List<Product> products = productDAO.getAllProducts();
                productDAO.delete(products.get(position));
                atualizaLista(position, v);
            }
        });

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listarProdutos, EditProductActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("product", products.get(position));
                listarProdutos.startActivity(intent);
            }
        });
        return viewLinhaLista;
    }

    public void atualizaLista(int position, View v){
        String mensagem = "Produto excluído com sucesso!";
        Snackbar snackbar = Snackbar.make(v, mensagem, Snackbar.LENGTH_LONG);
        snackbar.show();
        this.products.remove(position);
        this.notifyDataSetChanged();
    }

}