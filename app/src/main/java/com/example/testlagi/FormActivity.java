package com.example.testlagi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormActivity extends AppCompatActivity {

    EditText etNama, etPass;
    Button registerBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerBtn = (Button) findViewById(R.id.btnRegister);
        loginBtn = (Button) findViewById(R.id.btnLogin);
        etNama = findViewById(R.id.etNama);
        etPass = findViewById(R.id.etPassword);

        registerBtn.setOnClickListener(v -> {
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
        });

        loginBtn.setOnClickListener(v -> {
            String user = etNama.getText().toString().trim();
            String pass = etPass.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Harap isi data", Toast.LENGTH_SHORT).show();
            } else {
                if (pass.equals("Admin123") || pass.equals(user)) {
                    Intent intent = new Intent(this, HomeActivity.class);

                    intent.putExtra("user", user);

                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Password salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}