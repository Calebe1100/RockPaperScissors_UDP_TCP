import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ConfigSocketController {

    public static Stage _primaryStage;

    private boolean isConnectionUDP = false;

    private static String _cancel = "cancel";

    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        radioTCP.setToggleGroup(toggleGroup);
        radioUDP.setToggleGroup(toggleGroup);

        ObservableList<String> items = FXCollections.observableArrayList(
                "Pedra",
                "Papel",
                "Tesoura");

        selectOption.setItems(items);
    }

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonSend;

    @FXML
    private TextField ipField;

    @FXML
    private RadioButton radioTCP;

    @FXML
    private RadioButton radioUDP;

    @FXML
    private ChoiceBox<String> selectOption;

    @FXML
    private AnchorPane connectionGroup;

    @FXML
    void onCancel(ActionEvent event) {
        sendMessage(true);
    }

    @FXML
    void onSend(ActionEvent event) {
        sendMessage(false);
    }

    private void sendMessage(boolean isCancel) {
        if (this.isConnectionUDP || isCancel) {
            try (DatagramSocket socket = new DatagramSocket()) {
                byte[] messageToSend;
                messageToSend = isCancel ? _cancel.getBytes() : selectOption.valueProperty().getValue().getBytes();
                InetAddress ip = InetAddress.getByName(ipField.getText());
                DatagramPacket packageToSend = new DatagramPacket(messageToSend,
                        messageToSend.length,
                        ip,
                        5000);
                socket.send(packageToSend);

                byte[] receiver = new byte[1000];
                DatagramPacket receiverPackage = new DatagramPacket(receiver,
                        receiver.length);
                socket.receive(receiverPackage);

                String receiverMessage = new String(receiverPackage.getData());

                socket.close();
                if (!isCancel)
                    this.showMessage(receiverMessage, "Resultado:");

            } catch (Exception e) {
                this.showMessage(e.getMessage(), "Error:");
            }
        }

        if (!this.isConnectionUDP || isCancel) {
            try (Socket socket = new Socket(ipField.getText(), 5100)) {

                PrintWriter outBuffer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader inBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String messageToSend = isCancel ? _cancel : selectOption.valueProperty().getValue();
                outBuffer.println(messageToSend);

                String messageReceived = inBuffer.readLine();

                outBuffer.close();
                inBuffer.close();
                socket.close();
                if (!isCancel)
                    this.showMessage(messageReceived, "Resultado:");

            } catch (Exception e) {
                this.showMessage(e.getMessage(), "Error:");
            }

            if (isCancel) {
                System.exit(0);
                _primaryStage.close();
            }
        }
    }

    private void showMessage(String receiverMessage, String title) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(receiverMessage);
        alert.showAndWait();
    }

    @FXML
    void setRadioTCP(ActionEvent event) {
        if (radioTCP.isSelected()) {
            this.isConnectionUDP = false;
        }
    }

    @FXML
    void setRadioUDP(ActionEvent event) {
        if (radioUDP.isSelected()) {
            this.isConnectionUDP = true;
        }

    }

}