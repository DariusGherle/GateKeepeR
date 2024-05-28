package com.example.gatekeepr.Models.Comms;
import java.io.*;
import java.net.*;
public class EthernetCommunication {

    public static void main(String[] args) {
        String serverAddress = "192.168.1.177"; // IP-ul setat pe Arduino
        int port = 80; // Portul serverului pe Arduino

        try (Socket socket = new Socket(serverAddress, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Trimiterea unui mesaj către Arduino
            out.println("Hello, Arduino!");
// Citirea răspunsului de la Arduino
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Arduino: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

