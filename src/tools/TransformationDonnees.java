package tools;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class TransformationDonnees {

	public static String formatDate(DatePicker date) {
		if (date != null && !date.getValue().toString().isEmpty()) {
			String[] dateTab = date.getValue().toString().split("-");
			return dateTab[2] + "/" + dateTab[1] + "/" + dateTab[0];
		}
		return "";
	}

	public static int getIntValue(TextField champ) {
		try {
			return Integer.parseInt(champ.getText().trim());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public static double getDoubleValue(TextField champ) {
		try {
			return Double.parseDouble(champ.getText().trim());
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
