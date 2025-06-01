package com.banka.model;

import java.util.ArrayList;
import java.util.List;

public class Banka {
    private List<Musteri> musteriler = new ArrayList<>();
    private List<Hesap> hesaplar = new ArrayList<>();
    private List<Hareket> hareketler = new ArrayList<>();

    public List<Musteri> getMusteriler() { return musteriler; }
    public List<Hesap> getHesaplar() { return hesaplar; }
    public List<Hareket> getHareketler() { return hareketler; }
}
