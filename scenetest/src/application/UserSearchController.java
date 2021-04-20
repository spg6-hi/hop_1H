package application;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
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
	private VBox bookingVBox;
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
		FetchSearch();
	}
	
	public void FetchDatesByRoom(ComboBox<String> roomSelector, int hotelID, ComboBox<String> dateSelector) throws SQLException {
		dateSelector.getItems().clear();
		if (roomSelector.getValue().equals("Select a room type")) {
			dateSelector.getItems().add("Select a room to see dates");
			dateSelector.setValue("Select a room to see dates");
		} else {
			dateSelector.getItems().add("Select a date");
			dateSelector.setValue("Select a date");
		}
		Stack<String> dateStack = dbManager.getDates(hotelID, roomSelector.getValue());
		while (!dateStack.empty()) {
			String date = dateStack.pop();
			dateSelector.getItems().add(date);
		}
	}
	
	public void BookARoom(int hotelID, ComboBox<String> dateSelector, ComboBox<String> roomSelector) throws SQLException {
		if (	!dateSelector.getValue().equals("Select a room to see dates") && 
				!dateSelector.getValue().equals("Select a date") &&
				!roomSelector.getValue().equals("Select a room type") &&
				!usernameMenuItem.getText().equals("")){
			dbManager.bookRoom(hotelID, dateSelector.getValue(), roomSelector.getValue(), usernameMenuItem.getText());
			FetchSearch();
		}
	}
	
	public void CancelBooking(Room room) throws SQLException {
		dbManager.removeBooking(room.getHotelId(), room.getDate(), room.getRoomType(), room.getGuest());
		FetchSearch();
	}
	
	public void FetchSearch() throws SQLException {
		//We might want a separate java file for this to avoid bloating
				String textfieldName = nameTextField.getText();
				if (textfieldName.isBlank()) {
					textfieldName = "null";
				}
				String textfieldLocation = locationTextField.getText();
				if (textfieldLocation.isBlank()) {
					textfieldLocation = "null";
				}
				Stack<Hotel> hotelStack = dbManager.search(textfieldName, textfieldLocation);
				Label hotelLabel = new Label();
				Label bookingLabel = new Label();
				hotelLabel.setText("Hotel results:");
				bookingLabel.setText("Your current bookings:");
				resultVBox.getChildren().clear();
				resultVBox.getChildren().add(hotelLabel);
				bookingVBox.getChildren().clear();
				bookingVBox.getChildren().add(bookingLabel);
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
					roomSelector.getItems().add("Select a room type");
					roomSelector.setValue("Select a room type");
					ComboBox<String> dateSelector = new ComboBox<String>();
					dateSelector.getItems().add("Select a room to see dates");
					dateSelector.setValue("Select a room to see dates");
					button.setOnAction(e -> {
						try {
							BookARoom(hotel.getId(), dateSelector, roomSelector);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					});
					String rooms = "";
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
		            } else {
		            	rooms = "No vacancy";
		            }
					obsList.add(rooms);
					hotelInfo.setItems(obsList);
					hotelInfo.setMinHeight(70);
					hotelInfo.setMaxWidth(200);
					VBox bookingControl = new VBox();
					bookingControl.getChildren().add(button);
					bookingControl.getChildren().add(roomSelector);
					bookingControl.getChildren().add(dateSelector);
					hotelEntry.add(hotelInfo, 0, 0);
					hotelEntry.add(bookingControl, 1, 0);
					hotelEntry.setMaxHeight(70);
					resultVBox.getChildren().add(hotelEntry);
				}
				GridPane bookingEntries = new GridPane();
				Stack<Room> roomStack = dbManager.getBookings(usernameMenuItem.getText());
				while (!roomStack.isEmpty()) {
					Room booking = roomStack.pop();
					ListView<String> bookingInfo = new ListView<String>();
					ObservableList<String> obsList = FXCollections.observableArrayList();
					obsList.add(booking.getHotelName());
					obsList.add(booking.getDate());
					bookingInfo.setItems(obsList);
					Button cancelBooking = new Button();
					cancelBooking.setText("Cancel");
					cancelBooking.setOnAction(e -> {
						try {
							CancelBooking(booking);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					});
					VBox bookingControl = new VBox();
					bookingControl.getChildren().add(cancelBooking);
					bookingInfo.setMinHeight(70);
					bookingInfo.setMaxWidth(200);
					bookingEntries.add(bookingInfo, 0, 0);
					bookingEntries.add(bookingControl, 1, 0);
					bookingEntries.setMaxHeight(70);
					bookingVBox.getChildren().add(bookingEntries);
				}
	}
	
	public void ClearSearch(ActionEvent event) {
		ClearSearchResults();
	}
	
	public void ClearSearchResults() {
		resultVBox.getChildren().clear();
		bookingVBox.getChildren().clear();
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
		ClearSearchResults();
		stage.close();
	}
	
	public void CloseWindow(ActionEvent event) {
		Platform.exit();
	}
}
