package com.example.gatekeepr.Database;

import com.example.gatekeepr.Classes.Admin;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AdminsMapper implements RowMapper<Admin>{
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin admin = new Admin();
        admin.setAdresa_utilizator(rs.getString("adresa_utilizator"));
        admin.setParola(rs.getString("parola"));
        return admin;
    }
}
