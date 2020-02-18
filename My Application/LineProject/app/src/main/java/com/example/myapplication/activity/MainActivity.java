package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapter.viewholder.MemoAdapter;

public class MainActivity extends AppCompatActivity {

    private MemoAdapter adapter;
    private ImageButton mBtnWrite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnWrite = findViewById(R.id.write_Button);
        mBtnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MemoWriteActivity.class);
//                intent.putExtra("u_id", userId);
//                intent.putExtra("u_name",userName);
//                startActivityForResult(intent,REQUEST_TEST);
                startActivity(intent);
            }
        });
    }



    private void recyclerViewInit() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerMemoView);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MemoAdapter();
        recyclerView.setAdapter(adapter);
    }
}
