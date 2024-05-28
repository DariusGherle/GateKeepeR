package com.example.gatekeepr;

//import com.example.gatekeepr.Classes.Admin;
import com.example.gatekeepr.Database.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.example.gatekeepr"})
public class Testing {

    public static void main(String[] args) {

        // AccessLogs operations
        AccessLogsDB.selectAll();
        AccessLogsDB.insert(1, "2024-05-28 08:30:00", 123, "John Doe", 1);
        AccessLogsDB.update(1, "Jane Doe");
        AccessLogsDB.delete(1);

        // Admins operations
        AdminsDB.selectAll();
        AdminsDB.insert("admin@example.com", "password123");
        AdminsDB.update("admin@example.com", "newpassword123");
        AdminsDB.delete("admin@example.com");

        // Employees operations
        EmployeesDB.selectAll();
        EmployeesDB.insert(1, 1, "John", "Doe", "1234567890123", "image.jpg", "BT123", "DEV123", 1, 1, 1, "ABC123", 1);
        EmployeesDB.update(1, "Jane Doe");
        EmployeesDB.delete(1);

        // Gatekeepers operations
        GatekeepersDB.selectAll();
        GatekeepersDB.insert("gatekeeper@example.com", "password123");
        GatekeepersDB.update("gatekeeper@example.com", "newpassword123");
        GatekeepersDB.delete("gatekeeper@example.com");

        // Guests operations
        GuestsDB.selectAll();
        GuestsDB.insert(1, "John", "Doe", "1234567890123", "Meeting", 123, "Jane Doe", "2024-05-28 08:30:00", "2024-05-28 10:30:00");
        GuestsDB.update(1, "Jane Doe");
        GuestsDB.delete(1);

    }
}
