package com.banka.service;

import com.banka.model.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class BankaService {
    private Banka banka;
    private ReentrantLock lock = new ReentrantLock();

    public BankaService(Banka banka) {
        this.banka = banka;
    }

    public void musteriEkle(Musteri musteri) {
        banka.getMusteriler().add(musteri);
    }

    public void musteriSil(int musteriId) {
        banka.getMusteriler().removeIf(m -> m.getId() == musteriId);
        banka.getHesaplar().removeIf(h -> h.getMusteriId() == musteriId);
    }

    public void hesapAc(Hesap hesap) {
        banka.getHesaplar().add(hesap);
    }

    public void paraYatir(int hesapNo, double tutar) {
        for (Hesap h : banka.getHesaplar()) {
            if (h.getHesapNo() == hesapNo) {
                h.setBakiye(h.getBakiye() + tutar);
                banka.getHareketler().add(new Hareket(banka.getHareketler().size()+1, hesapNo, "Yatırma", tutar));
            }
        }
    }

    public void paraCek(int hesapNo, double tutar) {
        for (Hesap h : banka.getHesaplar()) {
            if (h.getHesapNo() == hesapNo && h.getBakiye() >= tutar) {
                h.setBakiye(h.getBakiye() - tutar);
                banka.getHareketler().add(new Hareket(banka.getHareketler().size()+1, hesapNo, "Çekme", tutar));
            }
        }
    }

    public void paraTransfer(int kaynakHesap, int hedefHesap, double tutar) {
        lock.lock();
        try {
            Hesap kaynak = null, hedef = null;
            for (Hesap h : banka.getHesaplar()) {
                if (h.getHesapNo() == kaynakHesap) kaynak = h;
                if (h.getHesapNo() == hedefHesap) hedef = h;
            }
            if (kaynak != null && hedef != null && kaynak.getBakiye() >= tutar) {
                kaynak.setBakiye(kaynak.getBakiye() - tutar);
                hedef.setBakiye(hedef.getBakiye() + tutar);
                banka.getHareketler().add(new Hareket(banka.getHareketler().size()+1, kaynakHesap, "Transfer Çıkış", tutar));
                banka.getHareketler().add(new Hareket(banka.getHareketler().size()+1, hedefHesap, "Transfer Giriş", tutar));
            }
        } finally {
            lock.unlock();
        }
    }

    public List<Hesap> musteriHesaplari(int musteriId) {
        return banka.getHesaplar().stream().filter(h -> h.getMusteriId() == musteriId).collect(Collectors.toList());
    }

    public List<Hareket> hesapHareketleri(int hesapNo) {
        return banka.getHareketler().stream().filter(h -> h.getHesapNo() == hesapNo).collect(Collectors.toList());
    }
}
