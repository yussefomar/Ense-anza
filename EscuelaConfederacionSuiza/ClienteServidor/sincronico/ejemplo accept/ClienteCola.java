/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

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
public class ClienteCola {

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
            
            String respuesta = "s";

            while (respuesta=="s") {
                System.out.println("Ingrese un mensaje para el servidor que funcionará como grupo de WhatsApp:");
                String mensaje = sc.nextLine();
            
                // Envía el mensaje al servidor
                out.writeUTF(mensaje);
            
                // Espera la respuesta del servidor y la muestra
                String serverResponse = in.readUTF();
                System.out.println("Respuesta del servidor: " + serverResponse);
                
                // Agregar lógica para salir del bucle si es necesario
                System.out.println("¿Desea enviar otro mensaje? (s/n): ");
                
                 respuesta = sc.nextLine().toLowerCase();
                 
            }
            System.out.println("Saliste no apretaste s "+ respuesta);
             
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