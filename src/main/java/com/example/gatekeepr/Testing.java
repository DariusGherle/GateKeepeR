package com.example.gatekeepr;

//import com.example.gatekeepr.Classes.Admin;
import com.example.gatekeepr.Database.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

import static com.example.gatekeepr.Database.RaportsGenerator.*;

@Configuration
@ComponentScan(basePackages = {"com.example.gatekeepr"})
public class Testing {

    public static void main(String[] args) {


         //Generare rapoarte
        getDivisionAttendanceReports(1); // report pt divizia specificata
        getAllDivisionsAttendanceReports();       // report pt toate diviziile precizata
        getAttendanceReportByMarca(101);          // report pt marca precizata

        System.out.println("\n");
         // Afiseaza toate înregistrările access_logs ca un array de string-uri
        String[] logsArray = getAccessLogsAsStringArray();
        for (String log : logsArray) {
            System.out.println(log);
        }   // chestia asta o bagi acolo intr-un listview sau cum e in javafx si bagi tot string-ul
    }
}
