package sendRecieved2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Cliente {
    public static void main(String[] args) {
        final String SERVER_HOST = "localhost";
        final int SERVER_PORT = 12345;

        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Crear una cola para mensajes
            Queue<String> messageQueue = new LinkedList<>();

            // Crear un hilo para escuchar y mostrar los mensajes del servidor
            Thread messageListener = new Thread(new MessageListener(in));
            messageListener.start();

            // Crear un hilo para enviar mensajes desde la cola
            Thread messageSender = new Thread(new MessageSender(out, messageQueue));
            messageSender.start();

            // Entrada de mensajes desde el usuario
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Cliente envía mensaje al servidor: ");
                String mensaje = scanner.nextLine();
                // Agregar mensaje a la cola
                synchronized (messageQueue) {
                    messageQueue.add("Cliente envía: " + mensaje);
                }
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

    static class MessageSender implements Runnable {
        private DataOutputStream out;
        private Queue<String> messageQueue;

        public MessageSender(DataOutputStream out, Queue<String> messageQueue) {
            this.out = out;
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Verificar si hay mensajes en la cola para enviar
                    synchronized (messageQueue) {
                        if (!messageQueue.isEmpty()) {
                            String mensaje = messageQueue.poll();
                            out.writeUTF(mensaje);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
