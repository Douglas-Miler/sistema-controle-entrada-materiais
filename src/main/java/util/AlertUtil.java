package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertUtil {

	public Alert createAlert(AlertType alertType, String contentText, ButtonType buttonType, String windowTitle) {
		Alert alert = new Alert(alertType, contentText, buttonType);
		alert.setTitle(windowTitle);
		alert.setHeaderText(null);
		
		return alert;
	}

	public Alert createAlert(AlertType alertType, String contentText, String windowTitle) {
		Alert alert = new Alert(alertType);
		alert.setContentText(contentText);
		alert.setTitle(windowTitle);
		alert.setHeaderText(null);
		
		return alert;
	}
	
}
