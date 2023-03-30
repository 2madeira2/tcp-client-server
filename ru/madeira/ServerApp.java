package ru.madeira;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(7777);
             Socket socket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            out.println("Привет! Попробуй угадать число, которое я загадал! Введите ответ: ");
            int randomNumber = new Random().nextInt(100);
            while (true) {
                String response = in.readLine();
                int num;
                try {
                    num = Integer.parseInt(response);
                } catch (Exception e) {
                    out.println("Введите, пожалуйста, число!");
                    continue;
                }
                if (num > randomNumber) {
                    out.println("LESS");
                } else if (num < randomNumber) {
                    out.println("MORE");
                } else {
                    out.println("EQUAL! Вы выиграли! Хотите ли начать заново игру? n - выход, чтобы продолжить - любой другой ввод");
                    if ("n".equals(in.readLine())) {
                        break;
                    } else {
                        out.println("Отлично! Начинаем новую игру! Какое число я загадал на этот раз?");
                        randomNumber = new Random().nextInt(100);
                    }
                }
            }
        }
    }
}
