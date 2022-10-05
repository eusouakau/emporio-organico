package com.example.emporio_organico.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.emporio_organico.R;
import com.example.emporio_organico.adapter.LinhaGerenciamentoAdapter;
import com.example.emporio_organico.dao.AppDatabase;
import com.example.emporio_organico.dao.ProductDAO;
import com.example.emporio_organico.entity.Product;

import java.util.List;

public class ManagerActivity extends AppCompatActivity {

    private ListView managerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        managerList = this.findViewById(R.id.productList);
        ProductDAO productDAO = AppDatabase.getInstance(getApplicationContext()).createProductDAO();
        getAll(productDAO.getAllProduct());
    }
    protected  void getAll(List<Product> products){
        managerList.setAdapter(new LinhaGerenciamentoAdapter(this, products));
    }
}