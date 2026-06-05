package com.example.testlagi;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MultiDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_detail);

        String nama = getIntent().getStringExtra("nama");
        String nim = getIntent().getStringExtra("nim");
        String prodi = getIntent().getStringExtra("prodi");

        ((TextView) findViewById(R.id.tvNama)).setText("Nama : " + nama);
        ((TextView) findViewById(R.id.tvNIM)).setText("NIM : " + nim);
        ((TextView) findViewById(R.id.tvProdi)).setText("Prodi : " + prodi);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}