package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class UserSearchController implements Initializable {
	//Do this to code with an object from the scene (variable = id)
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField locationTextField;
	@FXML
	private TextField availabilityTextField;
	@FXML
	private ListView<String> listView1;
	@FXML
	private GridPane resultGridPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// Nothing to see here, move along.
	}
	
	public void doSomething(ActionEvent event) {
		ObservableList<String> obsList = FXCollections.observableArrayList();
		obsList.add(nameTextField.getText());
		obsList.add(locationTextField.getText());
		obsList.add(availabilityTextField.getText());
		listView1.setItems(obsList);
	}
	
}
