package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class RobotView implements Initializable {

	@FXML
	private TextField input;

	@FXML
	private ListView<String> conversationList;

	private Robot robot;

	public RobotView() {
		robot = new Robot();
	}

	public void speak() {
		conversationList.getItems().add(input.getText());
		conversationList.getItems().add(robot.reply(input.getText()));
		conversationList.getItems().add("");
		input.clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		input.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				System.out.println("pressed Enter");
				speak();
			}
			if (e.getCode().equals(KeyCode.ESCAPE)) {
				input.getScene().getWindow().hide();
			}
		});

	}

}
