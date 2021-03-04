/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listenercalamp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
/**
 *
 * @author SISPC-0100
 */
public class ListenerCalamp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {

            DatagramSocket socketUDP = new DatagramSocket(1000);
            byte[] bufer = new byte[1024];

            while (true) {
                // Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion =
                        new DatagramPacket(bufer, bufer.length);

                // Leemos una petición del DatagramSocket
                socketUDP.receive(peticion);
                System.out.println("Recibo información del equipo");
                System.out.println("Datagrama del host: " + peticion.getAddress() + "desde el puerto remoto: " + peticion.getPort());
                byte[] bytes = peticion.getData();
                        
                String trama = ToHexadecimal.bytesToHex(bytes);
                System.out.println(ToHexadecimal.bytesToHex(bytes));
                
                WriteLog.writeFrames(trama);

                // Construimos el DatagramPacket para enviar la respuesta
                /*DatagramPacket respuesta =
                        new DatagramPacket(peticion.getData(), peticion.getLength(),
                                peticion.getAddress(), peticion.getPort());
                */
                // Enviamos la respuesta, que es un eco
                //socketUDP.send(respuesta);
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
    
}
