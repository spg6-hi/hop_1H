package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UserSearchController implements Initializable {
	//Do this to code with an object from the scene (variable = id)
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField locationTextField;
	@FXML
	private TextField availabilityTextField;
	@FXML
	private AnchorPane resultAnchorPane;
	@FXML
	private VBox resultVBox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// Nothing to see here, move along.
	}
	
	public void doSomething(ActionEvent event) {
		resultVBox.getChildren().clear();
		ObservableList<String> obsList = FXCollections.observableArrayList();
		obsList.add(nameTextField.getText());
		obsList.add(locationTextField.getText());
		obsList.add(availabilityTextField.getText());
		int i;
		for (i = 0; i < 8; i++) {
			GridPane hotelEntry = new GridPane();
			ListView<String> hotelInfo = new ListView<String>();
			hotelInfo.setItems(obsList);
			hotelInfo.setMinHeight(70);
			Button button = new Button();
			button.setText("Book selected date");
			ComboBox<String> dateSelector = new ComboBox<String>();
			dateSelector.getItems().add("Select a date");
			dateSelector.getItems().add("Choice 1");
			dateSelector.getItems().add("Choice 2");
			dateSelector.setValue("Select a date");
			VBox bookingControl = new VBox();
			bookingControl.getChildren().add(button);
			bookingControl.getChildren().add(dateSelector);
			hotelEntry.add(hotelInfo, 0, 0);
			hotelEntry.add(bookingControl, 1, 0);
			resultVBox.getChildren().add(hotelEntry);
		}
	}
	
}
