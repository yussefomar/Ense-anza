package sendRecieved2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Servidor {
    public static void main(String[] args) {
        final int PORT = 12345;
        ServerSocket serverSocket;
        List<Socket> clientSockets = new ArrayList<>();
        Queue<String> messageQueue = new LinkedList<>();

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor esperando clientes en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());
                clientSockets.add(clientSocket);

                // Crear un hilo para manejar la comunicación con el cliente y guardar mensajes
                Runnable messageReceiver = new MessageReceiver(clientSocket, messageQueue);
                Thread clientThread = new Thread(messageReceiver);
                clientThread.start();

                // Crear un hilo para enviar mensajes a los clientes
                Runnable messageSender = new MessageSender(clientSockets, messageQueue);
                Thread senderThread = new Thread(messageSender);
                senderThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MessageReceiver implements Runnable {
        private Socket socket;
        private Queue<String> messageQueue;

        public MessageReceiver(Socket socket, Queue<String> messageQueue) {
            this.socket = socket;
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());

                while (true) {
                    String mensaje = in.readUTF();
                    System.out.println("Mensaje recibido de " + socket.getInetAddress() + ": " + mensaje);

                     // Guarda el mensaje en la cola de mensajes de manera sincronizada
                    synchronized (messageQueue) {
                        messageQueue.add(mensaje);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class MessageSender implements Runnable {
        private List<Socket> clientSockets;
        private Queue<String> messageQueue;

        public MessageSender(List<Socket> clientSockets, Queue<String> messageQueue) {
            this.clientSockets = clientSockets;
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String mensaje;
                    synchronized (messageQueue) {
                        mensaje = messageQueue.poll();   // Obtiene un mensaje de la cola de manera sincronizada
                    }
                    if (mensaje != null) {
                        synchronized (clientSockets) {
                            for (Socket client : clientSockets) {
                                DataOutputStream clientOut = new DataOutputStream(client.getOutputStream());
                                clientOut.writeUTF(mensaje); // Envía el mensaje a los clientes
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
