package com.example.myapplication.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapter.viewholder.MemoAdapter;

public class MainActivity extends AppCompatActivity {

    private MemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void recyclerViewInit() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerShopIngredientView);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MemoAdapter();
        recyclerView.setAdapter(adapter);
    }
}
