package com.example.gatekeepr;

//import com.example.gatekeepr.Classes.Admin;
import com.example.gatekeepr.Database.DBOperationsAdmins;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.example.gatekeepr"})
public class Testing {

    public static void main(String[] args) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("adminbean.xml");
//        DBOperationsAdmins dbOperations = (DBOperationsAdmins) context.getBean("adminDBbean");
//
//        List<Admin> listaAdmini = dbOperations.getListaAdmin();
//
//        listaAdmini.stream().forEach(System.out::println);

    }
}
