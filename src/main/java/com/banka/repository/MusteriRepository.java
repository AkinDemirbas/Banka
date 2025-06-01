package com.banka.repository;

import com.banka.model.Musteri;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusteriRepository {
    private static final String JDBC_URL = "jdbc:h2:file:./banka-db";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASS = "";

    public MusteriRepository() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS musteri (id INT PRIMARY KEY, ad VARCHAR(100), soyad VARCHAR(100))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void musteriEkle(Musteri m) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement ps = conn.prepareStatement("INSERT INTO musteri (id, ad, soyad) VALUES (?, ?, ?)")) {
            ps.setInt(1, m.getId());
            ps.setString(2, m.getAd());
            ps.setString(3, m.getSoyad());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Musteri> tumMusteriler() {
        List<Musteri> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM musteri")) {
            while (rs.next()) {
                list.add(new Musteri(rs.getInt("id"), rs.getString("ad"), rs.getString("soyad")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
