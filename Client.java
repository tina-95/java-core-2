import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final String END_MESSAGE = "/end";
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {

        try {
            System.out.println("Open connection. Thread: " + Thread.currentThread().getName());
            openConnection();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка соединения с сервером. " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
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
        new Thread(() -> {
            System.out.println("Processing connection. Thread: " + Thread.currentThread().getName());

           try {
                while (true) {
                    String strFromServer = in.readUTF();
                    System.out.println(strFromServer);
                    if (strFromServer.equalsIgnoreCase(END_MESSAGE)) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
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

    public void sendMessage(String clientCommand) throws IOException {


        try {
            out.writeUTF(clientCommand);
            out.flush();


            if (END_MESSAGE.equals(clientCommand)) {
                out.writeUTF(END_MESSAGE);
                close(in, out, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения. " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String... args) {
        System.out.println("In main. Thread: " + Thread.currentThread().getName());


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Create client. Thread: " + Thread.currentThread().getName());
                new Client();
            }
        });

    }
}
