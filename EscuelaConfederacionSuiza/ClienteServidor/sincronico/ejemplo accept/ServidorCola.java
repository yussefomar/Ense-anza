import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCola {
    private InputStream isMiSocket;

    public static void main(String[] args){
        //descaramos un servidor de sockets
        ServerSocket miServidor=null;
        Socket socket;
        //variables para la entrada y salida de datos
        DataInputStream inSocket;
        DataOutputStream outSocket;

        try {
            //asignamos el puerto 1028 al servidor
            miServidor= new ServerSocket(12345);
            System.out.println("esperando conexiones");
        } catch (IOException e) {
            // TODO: handle exception
              System.out.println("exepcion"+e.getMessage());
        }

        while(true){
            try{
                //espera hasta que llegue una peticion de conexion y aceptarla
                socket=miServidor.accept();
                System.out.println("Cliente conectando..");
                System.out.println("El cliente tiene la IP:" + socket.getInetAddress());
                outSocket=new DataOutputStream(socket.getOutputStream());
                inSocket= new DataInputStream(socket.getInputStream());

                String mensaje= inSocket.readUTF();
                System.out.println("el mensaje recibido es "+ mensaje);

               outSocket.writeUTF("Hola desde el servidor...");

            }catch (IOException e) {
            // TODO: handle exception
              System.out.println("exepcion"+e.getMessage());
        }
        }

    }
}