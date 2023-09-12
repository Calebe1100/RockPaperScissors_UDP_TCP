import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {
    public static void main(String[] args) {

        new Thread(() -> ServerTCP()).start();
        new Thread(() -> ServerUDP()).start();

    }

    public static void ServerTCP() {
        try (ServerSocket serverPort = new ServerSocket(5100);) {

            System.out.println("Aguardando conexão TCP");
            Socket clientCall = serverPort.accept();

            PrintWriter outBuffer = new PrintWriter(clientCall.getOutputStream(), true);
            BufferedReader inBuffer = new BufferedReader(new InputStreamReader(clientCall.getInputStream()));

            String messageToSend = "";
            String messageReceived = "";

            RockPaperScissors rps = new RockPaperScissors();
            messageReceived = inBuffer.readLine();
            messageToSend = rps.getAnswer(messageReceived);

            outBuffer.println(messageToSend);

            outBuffer.close();
            inBuffer.close();
            clientCall.close();
            serverPort.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ServerUDP() {
        // get message from client
        try (DatagramSocket serverPort = new DatagramSocket(5000);) {
            System.out.println("Aguardando conexão UDP");

            byte[] packet = new byte[100];
            DatagramPacket packetReceived = new DatagramPacket(packet, packet.length);

            serverPort.receive(packetReceived);
            String messageReceived = new String(packetReceived.getData());

            // get answer
            RockPaperScissors rps = new RockPaperScissors();
            byte[] messageToSendByte = new byte[100];
            String messageToSend = rps.getAnswer(messageReceived);

            // Send message back to client
            messageToSendByte = messageToSend.getBytes();

            InetAddress ipClient = packetReceived.getAddress();
            int clientPort = packetReceived.getPort();

            DatagramPacket packetToSend = new DatagramPacket(messageToSendByte, messageToSendByte.length, ipClient,
                    clientPort);
            serverPort.send(packetToSend);

            serverPort.close();
            //
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
