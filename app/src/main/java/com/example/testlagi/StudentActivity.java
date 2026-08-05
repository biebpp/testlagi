package com.example.testlagi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testlagi.adapter.StudentAdapter;
import com.example.testlagi.database.DatabaseHelper;
import com.example.testlagi.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_student);

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
                    Toast.makeText(StudentActivity.this, "Data berhasil dihapus!", Toast.LENGTH_SHORT).show();
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
}