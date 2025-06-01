package com.banka.model;

public class Hesap {
    private int hesapNo;
    private int musteriId;
    private double bakiye;

    public Hesap(int hesapNo, int musteriId, double bakiye) {
        this.hesapNo = hesapNo;
        this.musteriId = musteriId;
        this.bakiye = bakiye;
    }

    public int getHesapNo() { return hesapNo; }
    public int getMusteriId() { return musteriId; }
    public double getBakiye() { return bakiye; }

    public void setBakiye(double bakiye) { this.bakiye = bakiye; }

    @Override
    public String toString() {
        return "Hesap No: " + hesapNo + ", Müşteri ID: " + musteriId + ", Bakiye: " + bakiye;
    }
}
