package com.banka.repository;

import com.banka.model.Hareket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HareketRepository {
    private static final String JDBC_URL = "jdbc:h2:./banka-db";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASS = "";

    public HareketRepository() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS hareket (hareketId INT PRIMARY KEY, hesapNo INT, tip VARCHAR(50), tutar DOUBLE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hareketEkle(Hareket h) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement ps = conn.prepareStatement("INSERT INTO hareket (hareketId, hesapNo, tip, tutar) VALUES (?, ?, ?, ?)");) {
            ps.setInt(1, h.getHareketId());
            ps.setInt(2, h.getHesapNo());
            ps.setString(3, h.getTip());
            ps.setDouble(4, h.getTutar());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Hareket> tumHareketler() {
        List<Hareket> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM hareket")) {
            while (rs.next()) {
                list.add(new Hareket(
                    rs.getInt("hareketId"),
                    rs.getInt("hesapNo"),
                    rs.getString("tip"),
                    rs.getDouble("tutar")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
