package com.example.gatekeepr.Database;
//import com.example.gatekeepr.Classes.Admin;
import com.example.gatekeepr.Models.Admin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Scanner;

@Service
public class DBOperationsAdmins {
//    private DataSource dataSource;
//    private  JdbcTemplate jdbcTemplateObject;
//    public void setDataSource(DataSource dataSource){
//        this.dataSource=dataSource;
//        this.jdbcTemplateObject=new JdbcTemplate(dataSource);
//    }
//
//    // temp testing below
//    public void insert(Scanner scanner){
//        System.out.println("Adresa utilizator: ");
//        String adresa = scanner.nextLine();
//        System.out.println("Parola");
//        String pass = scanner.nextLine();
//        String SQL = "insert into admins (adresa, pass) values (?, ?)";
//        jdbcTemplateObject.update(SQL, adresa, pass);
//    }
//
//    public void delete(String adresa_utilizator){
//        String SQL = "delete from admins where adresa_utilizator=?";
//        jdbcTemplateObject.update(SQL, adresa_utilizator);
//        System.out.println("delete works! adresa deleted with name" + adresa_utilizator);
//    }
//
//    public Admin getAdmin(String adresaAdmin) {
//        String SQL = "select * from admins where adresa_utilizator = ?";
//        Admin admin = jdbcTemplateObject.queryForObject(SQL, new Object[]{adresaAdmin}, new AdminsMapper());
//        return admin;
//    }
//    public List<Admin> getListaAdmin(){
//        String SQL = "select * from admins";
//        List<Admin> admins = jdbcTemplateObject.query(SQL, new AdminsMapper());
//        return admins;
//    }

}
