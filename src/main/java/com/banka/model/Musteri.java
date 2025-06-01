package com.banka.model;

public class Musteri {
    private int id;
    private String ad;
    private String soyad;

    public Musteri(int id, String ad, String soyad) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
    }

    public int getId() { return id; }
    public String getAd() { return ad; }
    public String getSoyad() { return soyad; }

    public void setAd(String ad) { this.ad = ad; }
    public void setSoyad(String soyad) { this.soyad = soyad; }

    @Override
    public String toString() {
        return id + ": " + ad + " " + soyad;
    }
}
