package udpclientv3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Asif
 */
public class UDPClientV3 {
    public static void main(String[] args) throws IOException, SocketException{
        Scanner input = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket();
        boolean running = true;
        while(running){
            // Sending Data
            String msg = input.nextLine();
            byte[] bufSend = msg.getBytes();
            InetAddress ipAddr = InetAddress.getByName("localhost");
            DatagramPacket dpSend = new DatagramPacket(bufSend, bufSend.length, ipAddr, 2245);
            socket.send(dpSend);

            // Receiving from Server
            byte[] bufRcv = new byte[1000];
            DatagramPacket dpRcv = new DatagramPacket(bufRcv, bufRcv.length);
            socket.receive(dpRcv);
            String receivedMsg = new String(dpRcv.getData(), 0, dpRcv.getLength());
            System.out.println("Client: " + receivedMsg);
            if(receivedMsg.equalsIgnoreCase("bye"))
                running = false;
        }
    }
    
}
