import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public static void main(String[] args) {
        final int PORT = 12345;
        ServerSocket serverSocket;
        List<Socket> clientSockets = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor esperando clientes en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());
                clientSockets.add(clientSocket);

                Runnable clientHandler = new ClientHandler(clientSocket, clientSockets);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private List<Socket> clientSockets;

        public ClientHandler(Socket socket, List<Socket> clientSockets) {
            this.socket = socket;
            this.clientSockets = clientSockets;
        }

        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    String mensaje = in.readUTF();
                    System.out.println("Mensaje recibido de " + socket.getInetAddress() + ": " + mensaje);

                    // Reenvía el mensaje a todos los clientes
                    synchronized (clientSockets) {
                        for (Socket client : clientSockets) {
                           
                                DataOutputStream clientOut = new DataOutputStream(client.getOutputStream());
                                clientOut.writeUTF(mensaje);
                            
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
