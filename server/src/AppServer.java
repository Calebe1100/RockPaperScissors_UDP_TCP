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

        start();
    }

    private static void start() {
        new Thread(() -> ServerTCP()).start();
        new Thread(() -> ServerUDP()).start();
    }

    public static void ServerTCP() {
        PrintWriter outBuffer;
        BufferedReader inBuffer;
        Socket clientCall;
        try (ServerSocket serverPort = new ServerSocket(5100);) {

            while (true) {
                System.out.println("Aguardando conexão TCP");
                clientCall = serverPort.accept();

                outBuffer = new PrintWriter(clientCall.getOutputStream(), true);
                inBuffer = new BufferedReader(new InputStreamReader(clientCall.getInputStream()));

                String messageToSend = "";
                String messageReceived = "";

                RockPaperScissors rps = new RockPaperScissors();
                messageReceived = inBuffer.readLine();
                messageToSend = rps.getAnswer(messageReceived);

                outBuffer.println(messageToSend);
                if (messageToSend.equalsIgnoreCase("cancel")) {
                    break;
                }
            }

            outBuffer.close();
            inBuffer.close();
            clientCall.close();
            serverPort.close();
            System.out.println("Servidor TCP encerrado...");
            Thread.currentThread().interrupt();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ServerUDP() {
        // get message from client
        try (DatagramSocket serverPort = new DatagramSocket(5000);) {
            DatagramPacket packetReceived;
            while (true) {
                System.out.println("Aguardando conexão UDP");
                byte[] packet = new byte[100];
                packetReceived = new DatagramPacket(packet, packet.length);

                serverPort.receive(packetReceived);
                String messageReceived = new String(packetReceived.getData());
                System.out.println("udp:" + messageReceived.length());
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
                if (messageReceived.equalsIgnoreCase("cancel")) {
                    break;
                }
            }
            serverPort.close();
            //
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
