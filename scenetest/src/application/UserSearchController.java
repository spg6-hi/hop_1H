package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
//SwitchUser imports for creating a new window
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class UserSearchController implements Initializable {
	static DBManager dbManager = new DBManager();
	private Stage stage;
	//Do this to code with an object from the scene (variable = id)
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField locationTextField;
	@FXML
	private AnchorPane resultAnchorPane;
	@FXML
	private VBox resultVBox;
	@FXML
	private MenuItem menuNewSearch;
	@FXML
	private MenuItem usernameMenuItem;
	@FXML
	private TextField usernameTextField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// Nothing to see here, move along.
	}
	
	public void Search(ActionEvent event) throws SQLException {
		//We might want a separate java file for this to avoid bloating.
		Stack<Hotel> hotelStack = dbManager.search(nameTextField.getText(), locationTextField.getText());
		resultVBox.getChildren().clear();
		while (!hotelStack.isEmpty()) {
			Hotel hotel = hotelStack.pop();
			Stack<String> roomStack = dbManager.getRooms(hotel.getId());
			ObservableList<String> obsList = FXCollections.observableArrayList();
			obsList.add(hotel.getHotelName());
			obsList.add(hotel.getLocation());
			GridPane hotelEntry = new GridPane();
			ListView<String> hotelInfo = new ListView<String>();
			Button button = new Button();
			button.setText("Book selected date");
			ComboBox<String> roomSelector = new ComboBox<String>();
			roomSelector.getItems().add("Select a room");
			roomSelector.setValue("Select a room");
			ComboBox<String> dateSelector = new ComboBox<String>();
			dateSelector.getItems().add("Select a room to see dates");
			dateSelector.setValue("Select a room to see dates");
			button.setOnAction(e -> {
				BookARoom(hotel.getId(), dateSelector, roomSelector);
			});
			String rooms = "";
			System.out.println(roomStack.isEmpty());
			int stopper = 0;
			while (!roomStack.empty() && stopper != 6) {
				String room = roomStack.pop();
				rooms += room;
				rooms += ", ";
				roomSelector.getItems().add(room);
				roomSelector.setOnAction(e -> {
					try {
						FetchDatesByRoom(roomSelector, hotel.getId(), dateSelector);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					});
				stopper++;
			}
			if (rooms.length() > 0) {
                rooms = rooms.substring(0, rooms.length()-2);
            }
			obsList.add(rooms);
			hotelInfo.setItems(obsList);
			hotelInfo.setMinHeight(70);
			VBox bookingControl = new VBox();
			bookingControl.getChildren().add(button);
			bookingControl.getChildren().add(roomSelector);
			bookingControl.getChildren().add(dateSelector);
			hotelEntry.add(hotelInfo, 0, 0);
			hotelEntry.add(bookingControl, 1, 0);
			resultVBox.getChildren().add(hotelEntry);
		}
	}
	
	/*public void Search(ActionEvent event) {
		//We might want a separate java file for this to avoid bloating.
		resultVBox.getChildren().clear();
		ObservableList<String> obsList = FXCollections.observableArrayList();
		obsList.add(nameTextField.getText());
		obsList.add(locationTextField.getText());
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
	}*/
	
	public void FetchDatesByRoom(ComboBox<String> roomSelector, int hotelID, ComboBox<String> dateSelector) throws SQLException {
		dateSelector.getItems().clear();
		dateSelector.getItems().add("Select a date");
		dateSelector.setValue("Select a date");
		Stack<String> dateStack = dbManager.getDates(hotelID, roomSelector.getValue());
		while (!dateStack.empty()) {
			String date = dateStack.pop();
			dateSelector.getItems().add(date);
		}
	}
	
	public void BookARoom(int hotelID, ComboBox<String> dateSelector, ComboBox<String> roomSelector) throws SQLException {
		Date date = new Date(Integer.parseInt(dateSelector.getValue().substring(0, 4)), Integer.parseInt(dateSelector.getValue().substring(5, 7)), Integer.parseInt(dateSelector.getValue().substring(8, dateSelector.getValue().length())));
		System.out.println(date.getYear() + "-" + date.getDate() + "-" + date.getDay());
		dbManager.bookRoom(hotelID, date, roomSelector.getValue(), usernameMenuItem.getText());
	}
	
	public void ClearSearch(ActionEvent event) {
		resultVBox.getChildren().clear();
		nameTextField.setText("");
		locationTextField.setText("");
	}
	
	public void SwitchUser(ActionEvent event) {
		//We might want a separate java file for this to avoid bloating.
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("SwitchUser.fxml"));
	        fxmlLoader.setController(this);
	        Scene scene = new Scene(fxmlLoader.load());
	        stage = new Stage();
	        stage.setTitle("New Window");
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	public void UserSwitched(ActionEvent event) {
		usernameMenuItem.setText(usernameTextField.getText());
		stage.close();
	}
	
	public void CloseWindow(ActionEvent event) {
		Platform.exit();
	}
}
