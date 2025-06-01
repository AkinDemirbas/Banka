package com.banka.model;

public class Hareket {
    private int hareketId;
    private int hesapNo;
    private String tip; // Yatırma, Çekme, Transfer
    private double tutar;

    public Hareket(int hareketId, int hesapNo, String tip, double tutar) {
        this.hareketId = hareketId;
        this.hesapNo = hesapNo;
        this.tip = tip;
        this.tutar = tutar;
    }

    public int getHareketId() { return hareketId; }
    public int getHesapNo() { return hesapNo; }
    public String getTip() { return tip; }
    public double getTutar() { return tutar; }

    @Override
    public String toString() {
        return "HareketID: " + hareketId + ", HesapNo: " + hesapNo + ", Tip: " + tip + ", Tutar: " + tutar;
    }
}
