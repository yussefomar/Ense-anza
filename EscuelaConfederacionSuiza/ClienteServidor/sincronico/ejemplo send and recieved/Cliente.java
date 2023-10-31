
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class Cliente{

    static String HOST = "localhost";
    static int PUERTO = 12345;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Socket socket;
        try {
            //Creamos nuestro socket
            socket = new Socket(HOST, PUERTO);
            socket.setSoTimeout(800*1000);
     
            DataOutputStream  out = new DataOutputStream(socket.getOutputStream());
            DataInputStream  in = new DataInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
            // Agregar lógica para salir del bucle si es necesario
            
            char respuesta = 'n';

            while (respuesta != 's') {
                System.out.println("Ingrese un mensaje para el servidor que funcionará como grupo de WhatsApp:");
                String mensaje = sc.nextLine();
            
                // Envía el mensaje al servidor
                out.writeUTF(mensaje);
            
                // Espera la respuesta del servidor y la muestra
                String serverResponse = in.readUTF();
                System.out.println("Respuesta del servidor: " + serverResponse);
                Scanner scc = new Scanner(System.in);
                System.out.println("Ingrese  s para salir");
                respuesta =  scc.nextLine().charAt(0) ;
               
                 
            }
           
            System.out.println("  ingresaste s"+ respuesta);
            
            //Cerramos la conexión
             socket.close();
 
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (SocketTimeoutException ste) {
            System.out.println("Tiempo de espera agotado...");
        }
        catch (IOException ioe){
            System.out.println("Exception: " + ioe.getMessage());
        }
       }
    }