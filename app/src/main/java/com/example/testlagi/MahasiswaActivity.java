package com.example.testlagi;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MahasiswaAdapter adapter;
    List<Mahasiswa> listSiswa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        isiDataSiswa();

        adapter = new MahasiswaAdapter(this, listSiswa);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void isiDataSiswa() {
        listSiswa.add(new Mahasiswa("Budi Santoso", "20230001", "Teknik Infomatika", 9.0));
        listSiswa.add(new Mahasiswa("Siti Rahayu", "20230002", "Teknik Informasi", 8.0));
        listSiswa.add(new Mahasiswa("Ahmad Fauzi", "20230003", "Teknik Infomatika", 7.5));
        listSiswa.add(new Mahasiswa("Dewi Lestari", "20230004", "Teknik Infomatika", 9.5));
        listSiswa.add(new Mahasiswa("Rizky Pratama", "20230005", "Teknik Infomatika", 7.0));
        listSiswa.add(new Mahasiswa("Nur Hidayah", "20230006", "Teknik Informasi", 8.0));
        listSiswa.add(new Mahasiswa("Dian Kusuma", "20230007", "Teknik Infomatika", 7.5));
        listSiswa.add(new Mahasiswa("Eko Prasetyo", "20230008", "Teknik Infomatika", 8.5));
    }
}