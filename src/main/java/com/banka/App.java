package com.banka;

import com.banka.model.*;
import com.banka.service.BankaService;
import com.banka.repository.MusteriRepository;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banka banka = new Banka();
        BankaService service = new BankaService(banka);
        System.out.println("Banka Yönetimi Uygulamasına Hoşgeldiniz!");
        MusteriRepository musteriRepo = new MusteriRepository();
        while (true) {
            System.out.println("\n1- Müşteri Ekle");
            System.out.println("2- Hesap Aç");
            System.out.println("3- Para Yatır");
            System.out.println("4- Para Çek");
            System.out.println("5- Para Transferi");
            System.out.println("6- Hesap Hareketleri");
            System.out.println("0- Çıkış");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            if (secim == 0) break;
            switch (secim) {
                case 1:
                    System.out.print("Müşteri ID: ");
                    int mid = scanner.nextInt();
                    System.out.print("Ad: ");
                    String ad = scanner.next();
                    System.out.print("Soyad: ");
                    String soyad = scanner.next();
                    musteriRepo.musteriEkle(new Musteri(mid, ad, soyad));
                    System.out.println("Müşteri veritabanına eklendi.");
                    break;
                case 2:
                    System.out.print("Hesap No: ");
                    int hno = scanner.nextInt();
                    System.out.print("Müşteri ID: ");
                    int mid2 = scanner.nextInt();
                    service.hesapAc(new Hesap(hno, mid2, 0));
                    break;
                case 3:
                    System.out.print("Hesap No: ");
                    int hno3 = scanner.nextInt();
                    System.out.print("Tutar: ");
                    double tutar3 = scanner.nextDouble();
                    service.paraYatir(hno3, tutar3);
                    break;
                case 4:
                    System.out.print("Hesap No: ");
                    int hno4 = scanner.nextInt();
                    System.out.print("Tutar: ");
                    double tutar4 = scanner.nextDouble();
                    service.paraCek(hno4, tutar4);
                    break;
                case 5:
                    System.out.print("Kaynak Hesap No: ");
                    int kaynak = scanner.nextInt();
                    System.out.print("Hedef Hesap No: ");
                    int hedef = scanner.nextInt();
                    System.out.print("Tutar: ");
                    double tutar5 = scanner.nextDouble();
                    service.paraTransfer(kaynak, hedef, tutar5);
                    break;
                case 6:
                    System.out.print("Hesap No: ");
                    int hno6 = scanner.nextInt();
                    for (Hareket h : service.hesapHareketleri(hno6)) {
                        System.out.println(h);
                    }
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
        System.out.println("Çıkılıyor...");
    }
}
