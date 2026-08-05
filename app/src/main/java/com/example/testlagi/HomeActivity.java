package com.example.testlagi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnCalc, btnMulti, btnMahasiswa, btnMakanan, btnCatatan, btnStudentCRUD, btnStudentSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnCalc = findViewById(R.id.btnCalc);
        btnMulti = findViewById(R.id.btnMulti);
        btnMulti = findViewById(R.id.btnMulti);
        btnMahasiswa = findViewById(R.id.btnMahasiswa);
        btnMakanan = findViewById(R.id.btnMakanan);
        btnCatatan = findViewById(R.id.btnCatatan);
        btnStudentCRUD = findViewById(R.id.btnStudentCRUD);
        btnStudentSQL = findViewById(R.id.btnStudentSQL);

        String user = getIntent().getStringExtra("user");
        ((TextView) findViewById(R.id.username)).setText("Selamat Datang! " + user);

        btnCalc.setOnClickListener(v -> {
            Intent intent = new Intent(this, CalculatorActivity.class);

            startActivity(intent);
        });

        btnMulti.setOnClickListener(v -> {
            Intent intent = new Intent(this, MultiActivity.class);

            startActivity(intent);
        });

        btnMahasiswa.setOnClickListener(v -> {
            Intent intent = new Intent(this, MahasiswaActivity.class);

            startActivity(intent);
        });

        btnMakanan.setOnClickListener(v -> {
            Intent intent = new Intent(this, MakananActivity.class);

            startActivity(intent);
        });

        btnCatatan.setOnClickListener(v -> {
            Intent intent = new Intent(this, CatatanActivity.class);

            startActivity(intent);
        });

        btnStudentCRUD.setOnClickListener(v -> {
            Intent intent = new Intent(this, StudentActivity.class);

            startActivity(intent);
        });

        btnStudentSQL.setOnClickListener(v -> {
            Intent intent = new Intent(this, StudentSQLActivity.class);

            startActivity(intent);
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}