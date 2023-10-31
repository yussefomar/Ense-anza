package sendRecieved;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
     

    public static void main(String[] args) {
        final String SERVER_HOST = "localhost";
        final int SERVER_PORT = 12345;

        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

           

            // Crear un hilo para escuchar y mostrar los mensajes del servidor
            Thread messageListener = new Thread(new MessageListener(in));
            messageListener.start();

            // Enviar mensajes al servidor junto con el ID del cliente
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Cliente envia mensaje al servidor ");
                String mensaje = scanner.nextLine();
                out.writeUTF("Cliente envia :  " + mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MessageListener implements Runnable {
        private DataInputStream in;

        public MessageListener(DataInputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String mensaje = in.readUTF();
                    System.out.println("Mensaje del servidor: " + mensaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}