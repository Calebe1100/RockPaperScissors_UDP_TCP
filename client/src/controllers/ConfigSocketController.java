package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ConfigSocketController {

    public void initialize() {
        // Carregue valores no ComboBox
        ObservableList<String> items = FXCollections.observableArrayList(
                "Opção 1",
                "Opção 2",
                "Opção 3");

        selectOption.setItems((ObservableList<String>) items);
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
    private ChoiceBox<?> selectOption;

    @FXML
    void onCancel(ActionEvent event) {

    }

    @FXML
    void onSend(ActionEvent event) {

    }

}