package com.example.piotrek.warehouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.piotrek.warehouse.data.ArticlesDataSource;
import com.example.piotrek.warehouse.ui.ListAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private ListAdapter adapter;
    private ArticlesDataSource articlesDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //Category category1 = new Category(0, "nabial", null);
        //Category category2 = new Category(0, "mleko", null/*articlesDataSource.getCategoryById(1)*/);

        //articlesDataSource.addCategory(category1);
        //articlesDataSource.addCategory(category2);

        adapter.clear();
        adapter.addAll(articlesDataSource.getCategories());
        articlesDataSource.close();

    }

    private void init() {
        list = (ListView) findViewById(R.id.list);
        adapter = new ListAdapter(getApplicationContext());
        list.setAdapter(adapter);
        articlesDataSource = new ArticlesDataSource(getApplicationContext());
        articlesDataSource.open();
        adapter.addAll(articlesDataSource.getCategories());
    }
}
