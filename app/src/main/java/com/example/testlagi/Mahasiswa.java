package com.example.testlagi;

public class Mahasiswa {
    private String nama;
    private String nim;
    private String prodi;
    private double ipk;

    public Mahasiswa(String nama, String nim, String prodi, double ipk) {
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
        this.ipk = ipk;
    }

    public String getNama() { return nama; }
    public String getNIM() { return nim; }
    public String getProdi() { return prodi; }
    public double getIpk() { return ipk; }
}
