import java.sql.Connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class ServerSocketService {
    public static Connection con = null;
    static List<User> userList = new ArrayList<>();
    static List<Book> bookList = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) throws SQLException {
        con = PostgresConnection.getInstance();
        try {

            ServerSocket serverSocket = new ServerSocket(6666);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                Socket socket = serverSocket.accept();
                client(socket, scanner);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void client(Socket socket, Scanner scanner) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(
                () -> {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        switch (bufferedReader.readLine().toLowerCase()){
                            case "user":
                                viewUsers();
                                bufferedWriter.write(userList.toString());
                                bufferedWriter.flush();
                                bufferedWriter.close();
                                break;
                            case "book":
                                viewBooks();
                                bufferedWriter.write(bookList.toString());
                                bufferedWriter.flush();
                                bufferedWriter.close();
                                break;
                        }
                        socket.close();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
    public static void viewUsers(){
        String sql ="select * from public.users";
        try {
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String first_name = resultSet.getString("first_name");
                int age = resultSet.getInt("age");
                userList.add(new User(id, username, first_name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewBooks(){
        String sql ="select * from public.books";
        try {
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                bookList.add(new Book(id, name, author));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


//        executorService.execute(
//                () -> {
//                    try {
//
//                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


//            System.out.println(bufferedReader.readLine());
//            System.out.println("malumot kiriting: ");
//            Scanner scanner = new Scanner(System.in);
//            String next = scanner.next();
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                        bufferedWriter.write("Hi " + bufferedReader.readLine() + " " + (count++) + "\n");
//                        bufferedWriter.flush();
//                        bufferedWriter.close();
//
//                        socket.close();
//
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//        );


//        new Thread(()->{
//            try {
//
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
////            System.out.println(bufferedReader.readLine());
////            System.out.println("malumot kiriting: ");
////            Scanner scanner = new Scanner(System.in);
////            String next = scanner.next();
////                try {
////                    sleep(1000);
////                } catch (InterruptedException e) {
////                    throw new RuntimeException(e);
////                }
//                bufferedWriter.write("Hi "+bufferedReader.readLine()+" "+(count++)+"\n");
//                bufferedWriter.flush();
//                bufferedWriter.close();
//
//                socket.close();
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();


class Client {
    public static void main(String[] args) {
        while (true) {
            try {
                sleep(1000 / 2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Scanner scanner = new Scanner(System.in);
                Socket socket = new Socket("localhost", 6666);

                System.out.println("\nUser\nBook\n");
                String next = scanner.next();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedWriter.write(next + "\n");
                bufferedWriter.flush();

                System.out.println(bufferedReader.readLine());
                socket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}


//    public static Connection con = null;
//       try {
//               con = PostgresConnection.getInstance();
//               String sql = "insert into users(username, name, age) " +
//               "VALUES ('" + getS() + "', '" + getS1() + "', '" + getS2() + "')";
//               con.createStatement().execute(sql);
//               } catch (SQLException e) {
//               throw new RuntimeException(e);
//               }