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
			hotelEntry.add(hotelInfo, 0, 0);
			hotelEntry.add(button, 1, 0);
			resultVBox.setMargin(hotelEntry, new Insets(0, 0, 0, 8));
			resultVBox.getChildren().add(hotelEntry);
		}
	}
	
}
