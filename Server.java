import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


/*
Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать сообщения,
        как на клиентской стороне, так и на серверной. Т.е. если на клиентской стороне написать «Привет»,
        нажать Enter, то сообщение должно передаться на сервер и там отпечататься в консоли. Если сделать то
        же самое на серверной стороне, то сообщение передается клиенту и печатается у него в консоли.
        Есть одна особенность, которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд.
        Такую ситуацию необходимо корректно обработать.
*/

public class Server  {


    private static final int SERVER_PORT = 8080;
    private static final String END_MESSAGE = "/end";
    private static DataOutputStream out;
    private static Socket socket;


    public Server(){
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            consoleChecker();



            while (true) {
                String entry = in.readUTF();

                if (entry.equals(END_MESSAGE)) {
                    break;
                }
                System.out.println("Received: " + entry);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String clientCommand) throws IOException {
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


// пишем данные с консоли в канал сокета для сервера

        if (clientCommand.trim().isEmpty()) {
            return;
        }

        if (socket.isClosed() || out == null) {
            JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения. Сервер не доступен. ",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try {
            out.writeUTF(clientCommand);
            out.flush();


            if (END_MESSAGE.equals(clientCommand)) {
                close(out, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения. " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void close(Closeable... objects) {
        for (Closeable o : objects) {
            try {
                if (o != null) {
                    o.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void consoleChecker(){
        Scanner scanner = new Scanner(System.in);

        Thread threadOut = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (scanner.hasNext()) {
                        String q = scanner.next();
                        try{
                            sendMessage(q);
                        }
                        catch (Exception e){

                        }
                        if (q.equalsIgnoreCase("close")) break;
                    }
                }
                close(socket);

            }
        });
        threadOut.start();

    }

    public static void main(String[] args) {
        System.out.println("In main. Thread: " + Thread.currentThread().getName());


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Create client. Thread: " + Thread.currentThread().getName());
                new Server();
            }
        });



    }


}
