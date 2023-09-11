import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ConfigSocketController {

    private boolean isConnectionUDP = false;

    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        radioTCP.setToggleGroup(toggleGroup);
        radioUDP.setToggleGroup(toggleGroup);

        ObservableList<String> items = FXCollections.observableArrayList(
                "Pedra",
                "Papel",
                "Tesoura");

        selectOption.setItems(items);

        fieldResult.setVisible(false);
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
    private TextArea fieldResult;

    @FXML
    private AnchorPane connectionGroup;

    @FXML
    void onCancel(ActionEvent event) {

    }

    @FXML
    void onSend(ActionEvent event) {
        if (this.isConnectionUDP) {
            try (DatagramSocket socket = new DatagramSocket()) {
                byte[] messageToSend;
                messageToSend = selectOption.valueProperty().getValue().getBytes();
                InetAddress ip = InetAddress.getByName(ipField.getText());
                DatagramPacket packageToSend = new DatagramPacket(messageToSend,
                        messageToSend.length,
                        ip,
                        5000);
                socket.send(packageToSend);

                // byte[] receiver = new byte[1000];
                // DatagramPacket receiverPackage = new DatagramPacket(receiver,
                // receiver.length);
                // socket.receive(receiverPackage);

                String receiverMessage = "teste";
                this.showMessage(receiverMessage);

                // SE NÃO TIVER MAIS NADA PARA FAZER
                // finaliza a conexão
                socket.close();
            } catch (Exception e) {

            }
        } else {

        }

    }

    private void showMessage(String receiverMessage) {
        this.togglePopupToMessageMode();
    }

    private void togglePopupToMessageMode() {
        connectionGroup.setVisible(false);
        fieldResult.setVisible(true);
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