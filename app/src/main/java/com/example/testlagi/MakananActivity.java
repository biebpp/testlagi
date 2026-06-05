package com.example.testlagi;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MakananActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MakananAdapter adapter;
    List<Makanan> listMakanan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        isiMakanan();

        adapter = new MakananAdapter(this, listMakanan);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void isiMakanan() {
        listMakanan.add(new Makanan("Nasi Goreng", "Nasi di Goreng", 10000));
        listMakanan.add(new Makanan("Mie Ayam", "Mie di Kasih Ayam", 10000));
        listMakanan.add(new Makanan("Bakso", "Pentol Pake Kuah", 15000));
        listMakanan.add(new Makanan("Mie Pangsit", "Mie di Kasih Pangsit", 10000));
        listMakanan.add(new Makanan("Nasi Padang", "Nasi Khas Padang", 10000));
        listMakanan.add(new Makanan("Mie Goreng", "Mie di Goreng", 5000));
        listMakanan.add(new Makanan("Nasi Ayam", "Nasi Sama Ayam", 10000));
        listMakanan.add(new Makanan("Mie Kuah", "Mie Pake Kuah", 5000));
    }
}