package com.example.testlagi;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CatatanActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "CatatanPrefs";
    private static final String KEY_CATATAN = "isi_catatan";

    EditText etCatatan;
    Button btnSimpan, btnHapus;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan);

        etCatatan = findViewById(R.id.etCatatan);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnHapus = findViewById(R.id.btnHapus);
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        muatCatatan();

        btnSimpan.setOnClickListener(v -> simpanCatatan());
        btnHapus.setOnClickListener(v -> hapusCatatan());

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void simpanCatatan() {
        String isiCatatan = etCatatan.getText().toString();
        prefs.edit().putString(KEY_CATATAN, isiCatatan).apply();
        Toast.makeText(this, "Catatan disimpan!", Toast.LENGTH_SHORT).show();
    }

    private void muatCatatan() {
        String isiCatatan = prefs.getString(KEY_CATATAN, "");
        etCatatan.setText(isiCatatan);
        etCatatan.setSelection(isiCatatan.length());
    }

    private void hapusCatatan() {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Catatan")
                .setMessage("Yakin ingin menghapus semua catatan")
                .setPositiveButton("Hapus", (dialog, which) -> {
                    prefs.edit().remove(KEY_CATATAN).apply();
                    etCatatan.setText("");
                    Toast.makeText(this, "Catatan dihapus.", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        simpanCatatan();
    }
}