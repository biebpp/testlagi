package com.example.testlagi;

public class Makanan {
    private String nama;
    private int harga;
    private String desc;

    public Makanan(String nama, String desc, int harga) {
        this.nama = nama;
        this.desc = desc;
        this.harga = harga;
    }

    public String getMakanan() { return nama; }
    public int getHarga() { return harga; }
    public String getDesc() { return desc; }
}
