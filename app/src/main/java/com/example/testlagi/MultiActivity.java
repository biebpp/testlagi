package com.example.testlagi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MultiActivity extends AppCompatActivity {

    Button btnDetail;
    EditText etNama, etNIM, etProdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        etNama = findViewById(R.id.etNama);
        etNIM = findViewById(R.id.etNIM);
        etProdi = findViewById(R.id.etProdi);
        btnDetail = (Button) findViewById(R.id.btnDetail);

        btnDetail.setOnClickListener(v -> {
            String nama = etNama.getText().toString().trim();
            String nim = etNIM.getText().toString().trim();
            String prodi = etProdi.getText().toString().trim();

            if (nama.isEmpty() || nim.isEmpty() || prodi.isEmpty()) {
                Toast.makeText(this, "Harap isi data", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, MultiDetailActivity.class);

                intent.putExtra("nama", nama);
                intent.putExtra("nim", nim);
                intent.putExtra("prodi", prodi);

                startActivity(intent);
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}