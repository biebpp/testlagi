package com.example.testlagi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class CalculatorActivity extends AppCompatActivity {

    EditText etAngka1, etAngka2;
    Button btnTambah, btnKurang, btnKali, btnBagi;
    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etAngka1 = findViewById(R.id.etAngka1);
        etAngka2 = findViewById(R.id.etAngka2);
        btnTambah = findViewById(R.id.btnTambah);
        btnKurang = findViewById(R.id.btnKurang);
        btnKali = findViewById(R.id.btnKali);
        btnBagi = findViewById(R.id.btnBagi);
        tvHasil = findViewById(R.id.tvHasil);

        btnTambah.setOnClickListener(v -> hitung("+"));
        btnKurang.setOnClickListener(v -> hitung("-"));
        btnKali.setOnClickListener(v -> hitung("*"));
        btnBagi.setOnClickListener(v -> hitung("/"));

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void hitung(String operator) {
        String s1 = etAngka1.getText().toString().trim();
        String s2 = etAngka2.getText().toString().trim();

        if(s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Isi kedua angka!", Toast.LENGTH_SHORT).show();
            return;
        }

        double a = Double.parseDouble(s1);
        double b = Double.parseDouble(s2);
        double hasil;

        switch (operator) {
            case "+": hasil = a + b;
            break;
            case "-": hasil = a - b;
            break;
            case "*": hasil = a * b;
            break;
            case "/":
                if (b == 0) {
                    tvHasil.setText("Error: Tidak bisa bagi dengan 0!");
                    return;
                } else {
                    hasil = a / b;
                } break;
            default: return;
        }

        if (hasil == (long) hasil) {
            tvHasil.setText("Hasil: " + (long) hasil);
        } else {
            tvHasil.setText("Hasil : " + hasil);
        }
    }
}