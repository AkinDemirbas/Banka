package com.banka.repository;

import com.banka.model.Hesap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HesapRepository {
    private static final String JDBC_URL = "jdbc:h2:./banka-db";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASS = "";

    public HesapRepository() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS hesap (hesapNo INT PRIMARY KEY, musteriId INT, bakiye DOUBLE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hesapEkle(Hesap h) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement ps = conn.prepareStatement("INSERT INTO hesap (hesapNo, musteriId, bakiye) VALUES (?, ?, ?)");) {
            ps.setInt(1, h.getHesapNo());
            ps.setInt(2, h.getMusteriId());
            ps.setDouble(3, h.getBakiye());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Hesap> tumHesaplar() {
        List<Hesap> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM hesap")) {
            while (rs.next()) {
                list.add(new Hesap(rs.getInt("hesapNo"), rs.getInt("musteriId"), rs.getDouble("bakiye")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
