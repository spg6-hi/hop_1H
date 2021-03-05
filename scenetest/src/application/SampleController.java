package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class SampleController implements Initializable {
	Scene scene;
	TextField nametxt; 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// Nothing to see here, move along.
	}
	
	public void giveInfo(Scene s) {
		scene = s;
		nametxt = (TextField) scene.lookup("#Nametxt");
	}
	
	public void doSomething(ActionEvent event) {
		System.out.println(nametxt.getText() + " from Name field");
	}
	
}
