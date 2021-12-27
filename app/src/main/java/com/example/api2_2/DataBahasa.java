package com.example.api2_2;

public class DataBahasa {
    private String nama, deskripsi, logo, contohKode, readMore;

    public DataBahasa(String nama, String deskripsi, String readMore, String contohKode, String logo) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.readMore = readMore;
        this.contohKode = contohKode;
        this.logo = logo;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getReadMore() {
        return readMore;
    }

    public String getContohKode() {
        return contohKode;
    }

    public String getLogo() {
        return logo;
    }
}
