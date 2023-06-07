package ru.netology.javacore;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@ToString
@AllArgsConstructor
public class TodoServer {

    @ToString
    static class Request {

        String type, task;

        public Request(String type, String task) {
            this.type = type;
            this.task = task;
        }
    }

    private final int port;
    private final Todos todos;

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("\nЗапуск сервера по адресу " + this.port + "... \nСервер запущен...\n");
            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    System.out.println("Новое подключение принято!");
                    System.out.println("Адрес клиента: " + clientSocket.getInetAddress() +
                            " , port: " + clientSocket.getPort());
                    String json = in.readLine();
                    System.out.println("Сообщение клиента: " + json);
                    Request r = new Gson().fromJson(json, Request.class);
                    switch (r.type) {
                        case "ДОБАВЛЯТЬ":
                            System.out.println("Добавить задачу '" + r.task + "' в список задач");
                            todos.addTask(r.task);
                            break;
                        case "УДАЛИТЬ":
                            System.out.println("Удалить задачу '" + r.task + "' из списка дел");
                            todos.removeTask(r.task);
                            break;
                    }
                    System.out.println("Отправить список задач клиенту... ");
                    out.println(todos.getTasks());
                    System.out.println("Сделано!\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Не удается запустить сервер!");
            e.printStackTrace();
        }
    }
}
