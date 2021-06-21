package Controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Cursor;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Viewcontrol {
	private static int clic = 0;
	private static BorderPane root = null;
	private static final ObjectProperty<Cursor> cursor = new SimpleObjectProperty<>(Cursor.DEFAULT);

	public Viewcontrol() {

	}

	public Viewcontrol(BorderPane navroot) {
		if (root == null) {
			root = navroot;
		}

	}

	public BorderPane Viewnavigation() {

		return root;

	}

	public void navview() {
		clic += 1;
	}

	public int getClic() {
		System.out.println("getnbclic**" + clic);
		return clic;
	}

	@SuppressWarnings("static-access")
	public void setClic(int clic) {
		this.clic = clic;
		System.out.println("nbclic**" + clic);
	}

	public boolean isclose(Stage stage) {
		clic = 0;
		return (stage != null) ? true : false;

	}

	public ObjectProperty<Cursor> getCursor() {
		return cursor;
	}

	public void setCursor(Cursor c) {
		cursor.set(c);
	}

}
