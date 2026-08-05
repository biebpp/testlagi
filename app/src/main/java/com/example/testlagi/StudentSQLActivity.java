package com.example.testlagi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testlagi.adapter.StudentAdapter;
import com.example.testlagi.database.DatabaseHelper;
import com.example.testlagi.model.Student;
import com.example.testlagi.network.DbConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentSQLActivity extends AppCompatActivity {

    private EditText etNis, etName, etMajor;
    private Button btnSave;
    private RecyclerView rvStudents;

    private DatabaseHelper databaseHelper;
    private StudentAdapter studentAdapter;
    private List<Student> studentList = new ArrayList<>();

    private boolean isEditMode = false;
    private int selectedStudentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sql);

        etNis = findViewById(R.id.etNis);
        etName = findViewById(R.id.etName);
        etMajor = findViewById(R.id.etMajor);
        btnSave = findViewById(R.id.btnSave);
        rvStudents = findViewById(R.id.rvStudents);

        databaseHelper = new DatabaseHelper(this);

        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        loadStudentData();

        btnSave.setOnClickListener(v -> saveStudentData());
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void loadStudentData() {
        studentList.clear();
        studentList.addAll(databaseHelper.getAllStudent());

        studentAdapter = new StudentAdapter(this, studentList, new StudentAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Student student) {
                isEditMode = true;
                selectedStudentId = student.getId();
                etNis.setText(student.getNis());
                etName.setText(student.getName());
                etMajor.setText(student.getMajor());
                btnSave.setText("Perbarui Data Siswa");
            }

            @Override
            public void onDeleteClick(Student student) {
                showDeleteDialog(student);
            }
        });
        rvStudents.setAdapter(studentAdapter);
    }

    private void saveStudentData() {
        String nis = etNis.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String major = etMajor.getText().toString().trim();

        if (TextUtils.isEmpty(nis) || TextUtils.isEmpty(name) || TextUtils.isEmpty(major)) {
            Toast.makeText(this, "Harap isi seluruh kolom input!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEditMode) {
            Student student = new Student(selectedStudentId, nis, name, major);
            databaseHelper.updateStudent(student);
            Toast.makeText(this, "Data berhasil diperbarui!", Toast.LENGTH_SHORT).show();
            isEditMode = false;
            btnSave.setText("Simpan Data");
        } else {
            Student student = new Student(nis, name, major);
            databaseHelper.addStudent(student);
            Toast.makeText(this, "Data berhasil disimpan lokal!", Toast.LENGTH_SHORT).show();
            createStudentOnServer(nis, name, major);
        }
        clearForm();
        loadStudentData();
    }

    private void showDeleteDialog(Student student) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Data")
                .setMessage("Yakin ingin menghapus " + student.getName() + "?")
                .setPositiveButton("Ya", (dialog, which) -> {
                    databaseHelper.deleteStudent(student.getId());
                    Toast.makeText(StudentSQLActivity.this, "Data berhasil dihapus!", Toast.LENGTH_SHORT).show();
                    loadStudentData();
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    private void clearForm() {
        etNis.setText("");
        etName.setText("");
        etMajor.setText("");
        selectedStudentId = -1;
    }

    private void readDataFromServer() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, DbConfig.URL_READ,
                response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String status = jsonObject.getString("status");

                if (status.equals("success")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    studentList.clear();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        Student student = new Student(
                                item.getInt("id"),
                                item.getString("nis"),
                                item.getString("nama"),
                                item.getString("jurusan")
                        );
                        studentList.add(student);
                    }
                    studentAdapter.notifyDataSetChanged();
                }

            }catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(StudentSQLActivity.this, "Gagal Parsing Response JSON!", Toast.LENGTH_SHORT).show();
            }

                },
                error -> Toast.makeText(StudentSQLActivity.this, "Koneksi Server Gagal!", Toast.LENGTH_SHORT).show()
                );
        queue.add(stringRequest);
    }

    private void createStudentOnServer(String nis, String nama, String jurusan) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DbConfig.URL_CREATE,
                response -> {
            Toast.makeText(StudentSQLActivity.this, "Tersimpan di Server MySQL", Toast.LENGTH_SHORT).show();
            readDataFromServer();
                },
                error -> Toast.makeText(StudentSQLActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show()
                ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nis", nis);
                params.put("nama", nama);
                params.put("jurusan", jurusan);
                return params;
            }
        };
        queue.add(stringRequest);
    }
}